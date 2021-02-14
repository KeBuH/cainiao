package ru.cainiao.service;

import lombok.RequiredArgsConstructor;
import org.postgresql.util.PGInterval;
import org.postgresql.util.PGmoney;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.cainiao.model.Company;
import ru.cainiao.model.RoutePart;
import ru.cainiao.repo.DeliveryRepo;
import ru.cainiao.utils.IntervalFormatter;
import ru.cainiao.web.dto.DeliveryInfo;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class DeliveryInfoBuilder {

    private final DeliveryRepo repo;

    private Company firstMile;
    private Company transport;
    private Company lastMile;
    private PGmoney totalPrice = new PGmoney(0);
    private final PGInterval totalTime = new PGInterval();

    public DeliveryInfoBuilder firstMile(final String from, final String to) {
        firstMile = repo.cheapestCompanyRoutePart(RoutePart.FIRST_MILE, from, to);
        increaseTotal(firstMile);
        return this;
    }

    public DeliveryInfoBuilder transport(final String from, final String to) {
        transport = repo.cheapestCompanyRoutePart(RoutePart.TRANSPORT, from, to);
        increaseTotal(transport);
        return this;
    }

    public DeliveryInfoBuilder lastMile(final String from, final String to) {
        lastMile = repo.cheapestCompanyRoutePart(RoutePart.LAST_MILE, from, to);
        increaseTotal(lastMile);
        return this;
    }

    private void increaseTotal(final Company company) {
        totalPrice = new PGmoney(totalPrice.val + company.getPrice().val);
        company.getTime().add(totalTime);
    }

    public DeliveryInfo build() {
        final DeliveryInfo deliveryInfo = new DeliveryInfo();
        if (firstMile != null) {
            deliveryInfo.setFirstMile(firstMile.getCompanyName());
        }
        if (transport != null) {
            deliveryInfo.setTransport(transport.getCompanyName());
        }
        if (lastMile != null) {
            deliveryInfo.setLastMile(lastMile.getCompanyName());
        }
        deliveryInfo.setTotalTime(IntervalFormatter.toString(totalTime));
        deliveryInfo.setTotalCost(totalPrice.getValue());
        return deliveryInfo;
    }

}
