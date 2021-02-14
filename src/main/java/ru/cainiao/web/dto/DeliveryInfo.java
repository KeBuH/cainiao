package ru.cainiao.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryInfo {

    @JsonProperty("First Mile")
    private String firstMile;
    @JsonProperty("Transport")
    private String transport;
    @JsonProperty("Last Mile")
    private String lastMile;
    @JsonProperty("Total Cost")
    private String totalCost;
    @JsonProperty("Total Time")
    private String totalTime;

}
