package ru.cainiao.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.cainiao.model.Company;
import ru.cainiao.model.RoutePart;



@Repository
@RequiredArgsConstructor
public class DeliveryRepo {

    private static final String QUERY_TEMPLATE = "select company_name, price, time from company where route_part = ? and pickup_city = ? and delivery_city = ? order by price limit 1";

    private final JdbcTemplate template;

    public Company cheapestCompanyRoutePart(final RoutePart part,
                                            final String from,
                                            final String to) {
        return template.queryForObject(QUERY_TEMPLATE, new Object[] { part.getValue(), from, to }, new CompanyMapper());
    }
}
