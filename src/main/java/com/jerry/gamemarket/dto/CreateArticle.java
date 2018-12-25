package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 14:32
 */
@Data
public class CreateArticle {
    private String orderId;
    private String canteenId;
    private String articleName;
    private String articleImg;
    private String articleText;
    private String userId;
    private String userName;
    private Float score;
}
