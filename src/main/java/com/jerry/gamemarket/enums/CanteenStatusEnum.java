package com.jerry.gamemarket.enums;

import lombok.Getter;

import javax.persistence.Enumerated;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
@Getter
public enum CanteenStatusEnum {

    Canteen_CLOSE(1,"打烊"),
    Canteen_OPEN(2,"开业"),
            ;
    private Integer code;
    private String message;
    CanteenStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
}
