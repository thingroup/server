package com.jerry.gamemarket.enums;

import lombok.Getter;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
@Getter
public enum CouponStatusEnum {
    In(0,"可使用"),
    OUT(1,"已经过期")
            ;
    private Integer code;
    private String message;
    CouponStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
}
