package com.jerry.gamemarket.utils;

import java.util.Random;

public class KeyUtil {
//    生成唯一主键：时间+随机数
//    防止并发
    public static synchronized String genUniquekey(){
        Random random = new Random();
//        6位随机数
        Integer a = random.nextInt(9000)+100000;
        return System.currentTimeMillis()+String.valueOf(a);

    }
}
