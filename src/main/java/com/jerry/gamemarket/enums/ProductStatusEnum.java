package com.jerry.gamemarket.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP(0,"正常出售"),
    DOWN(1,"已经下架")
    ;
    private Integer code;
    private String message;
    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
}
