package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 14:01
 */
@Data
public class CanLikeDTO {
    private String userId;
    private Integer type;
    private Timestamp updateTime;
    private String caId;
    private String canCommentId;
}
