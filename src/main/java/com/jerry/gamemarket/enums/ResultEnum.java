package com.jerry.gamemarket.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_NOT_ENOUGH(11,"库存不足"),
    COUPON_NOT_EXIST(12,"优惠券不存在"),
    COUPON_NOT_ENOUGH(13,"优惠券库存不足"),
    Order_NOT_EXIST(14,"订单不存在"),
    OrderDetail_NOT_EXIST(15,"订单详情不存在"),
    Canteen_NOT_EXIST(16,"餐厅信息不存在"),
    Canteen_Available(17,"尚有空余包间"),
    Canteen_Full(18,"暂无包间"),
    ORDER_STATUS_ERROR(19,"订单状态不正确"),
    UPDATE_STATUS_ERROR(20,"更新状态失败"),
    ORDER_DELETL_EMPTY(21,"订单无商品详情"),
    ORDER_PAY_STATUS_ERROR(22,"订单支付状态错误"),
    PARAM_ERROR(1,"参数不正确"),
    EMPTY_ERROR(2,"购物车不能为空"),
    OPENID_ERROR(3,"该订单不属于当前用户")
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
