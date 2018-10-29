package com.jerry.gamemarket.exception;

import com.jerry.gamemarket.enums.ResultEnum;

public class GameException extends RuntimeException {
    private  Integer code;

    public GameException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public GameException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
