package com.jerry.gamemarket.dto;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/11/30 0030 20:31
 */
public class ArticleDTO {
    private Integer articleId;
    private String articleName;
    private String articleText;
    private String userId;
    private Timestamp updateTime;
    private Float score;
    private Integer likes;
    private Integer dislikes;
    private String userName;
}
