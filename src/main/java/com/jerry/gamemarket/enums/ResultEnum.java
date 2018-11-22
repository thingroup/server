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
    FINISH_ORDER_SUCCESS(40 , "完结订单成功"),
    UPDATE_STATUS_ERROR(20,"更新状态失败"),
    ORDER_DELETL_EMPTY(21,"订单无商品详情"),
    ORDER_PAY_STATUS_ERROR(22,"订单支付状态错误"),
    WECHAT_MP_ERROR(23 , "微信网页授权失败"),
    PAY_MONEY_NOT_EQUAL(24 , "支付金额不一致"),

    PRODUCT_OFF_SALE_FAIL(25 , "商品下架失败"),
    PRODUCT_ON_SALE_FAIL(26 , "商品下架失败"),

    PRODUCT_OFF_SALE_SUCCESS(27 , "商品下架成功"),

    PRODUCT_ON_SALE_SUCCESS(28 , "商品下架成功"),

    PRODUCT_UPDATE_SUCCESS(29 , "商品更新成功"),

    CATEGORY_NOT_FOUND(30 , "商品类目没有找到"),

    CATEGORY_UPDATE_SUCCESS(31 , "商品类目更新成功"),

    CATEGORY_UPDATE_FAIL(32 , "商品类目更新失败"),

    LOGIN_FAIL(33 , "登录失败"),

    LOGIN_SUCCESS(34 , "登录成功"),

    LOGOUT_SUCCESS(35 , "登出成功"),
    CANCEL_ORDER_SUCCESS(36 , "取消订单成功"),
    CANTEEN_NOT_HAVEGOODS(37 , "商家还没有上传商品"),
    PRODUCT_STATUS_ERROR(38, "商品状态不正确"),
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
