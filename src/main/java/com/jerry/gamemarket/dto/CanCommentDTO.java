package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 14:30
 */
@Data
public class CanCommentDTO {
    private Integer canCommentId;
    private String userId;
    private String userName;
    private String commentText;
    private Integer likes;
    private Integer dislikes;
    private Integer caId;
    private Timestamp updateTime;
    private String lastUname;
    private String lastUid;
}
