package com.jerry.gamemarket.dto;

import lombok.Data;

//购物车
@Data
public class CartDTO {
    private String canteenId;
//    产品id
    private  String productId;
//    数量
    private  Integer productQuantity;

    private  Integer couponQuantity;


    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
