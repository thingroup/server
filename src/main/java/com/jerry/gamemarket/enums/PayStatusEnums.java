package com.jerry.gamemarket.enums;

import lombok.Getter;

/**
 * 支付状态
 * Created by Administrator on 2017/10/14 0014.
 */
@Getter
public enum PayStatusEnums{
    NEW(0 , "未支付"),
    FINISH(1 , "已支付"),
    WAIT(2,"等待支付")
    ;

    private Integer code;

    private String msg;

    PayStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
