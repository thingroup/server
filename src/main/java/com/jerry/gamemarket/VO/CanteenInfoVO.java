package com.jerry.gamemarket.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CanteenInfoVO {
    @JsonProperty("id")
    private String canteenId;
    @JsonProperty("name")
    private String canteenName;
    @JsonProperty("price")
    private BigDecimal personConsume;
    @JsonProperty("description")
    private String canteenDescription;
    @JsonProperty("icon")
    private String canteenIcon;
}
