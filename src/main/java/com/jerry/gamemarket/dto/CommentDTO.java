package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/11/30 0030 20:32
 */
@Data
public class CommentDTO {
    private Integer articleCommentId;
    private String userId;
    private String userName;
    private String commentText;
    private Integer likes;
    private Integer dislikes;
    private Integer articleId;
    private Timestamp updateTime;
    private String lastUname;
    private String lastUid;
    private Integer role;
}
