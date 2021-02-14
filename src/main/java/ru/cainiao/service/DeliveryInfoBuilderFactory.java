package ru.cainiao.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;



@Component
public class DeliveryInfoBuilderFactory {

    @Lookup
    public DeliveryInfoBuilder getBuilder() {
        return null;
    }

}
