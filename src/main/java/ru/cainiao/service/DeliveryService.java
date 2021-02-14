package ru.cainiao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cainiao.web.dto.DeliveryInfo;


@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryInfoBuilderFactory factory;

    public DeliveryInfo calcDelivery(final String from, final String to) {
        return factory.getBuilder()
                .firstMile(from, from)
                .transport(from, to)
                .lastMile(to, to)
                .build();
    }
}
