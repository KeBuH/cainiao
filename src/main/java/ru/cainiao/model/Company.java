package ru.cainiao.model;

import lombok.Data;
import org.postgresql.util.PGInterval;
import org.postgresql.util.PGmoney;



@Data
public class Company {

    private String companyName;
    private RoutePart part;
    private String pickupCity;
    private String deliveryCity;
    private PGmoney price;
    private PGInterval time;

}
