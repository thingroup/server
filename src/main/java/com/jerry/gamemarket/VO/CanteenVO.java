package com.jerry.gamemarket.VO;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jerry.gamemarket.entity.CanteenInfo;
import lombok.Data;

import java.util.List;
@Data
public class CanteenVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("canteens")
    private List<CanteenInfoVO> canteenInfoVOList;
}
