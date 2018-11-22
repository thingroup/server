package com.jerry.gamemarket.utils.serializer;

import com.jerry.gamemarket.enums.CodeEnums;

/**
 * Created by Jerry on 2018/11/21 0001.
 */
public class EnumsUtils {

    private EnumsUtils(){}

    public static <T extends CodeEnums>T getEnumsByCode(Integer code , Class<T> enums) {
        for(T e : enums.getEnumConstants()) {
            if( e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
