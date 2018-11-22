package com.jerry.gamemarket.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 李兆杰
 * 2018-11-22 17:20
 */
@Data
public class CanteenForm {


    private String canteenId;

    /** 名字. */
    private String canteenName;
    private String canteenAddress;
    /** 人均消费. */
    private BigDecimal personConsume ;

    /** 包间数量. */
    private Integer canteenRoomStock;

    /** 描述. */
    private String canteenDescription;

    /** 小图. */
    private String canteenIcon;

    /** 类目编号. */
    private Integer categoryType;
}
