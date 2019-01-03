package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 15:36
 */
@Data
public class LikeDTO {
    private String userId;
    private Integer type;
    private String articleId;
    private String articleCommentId;
    private String canteenId;
}
