package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 13:20
 */
@Data
public class CanArticleDTO {
    private Integer caId;
    private String canteenId;
    private String userId;
    private String userName;
    private Integer likes;
    private Integer dislikes;
    private Timestamp updateTime;
    private String img;
    private String text;
    private Float score;
    private String name;
}
