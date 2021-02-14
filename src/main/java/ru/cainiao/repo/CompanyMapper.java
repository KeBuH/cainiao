package ru.cainiao.repo;

import org.postgresql.util.PGmoney;
import org.springframework.jdbc.core.RowMapper;
import ru.cainiao.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.util.PGInterval;


public class CompanyMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet resultSet, int i) throws SQLException {
        Company company = new Company();
        company.setCompanyName(resultSet.getString("company_name"));
        company.setPrice(resultSet.getObject("price", PGmoney.class));
        company.setTime(resultSet.getObject("time", PGInterval.class));
        return company;
    }
}
