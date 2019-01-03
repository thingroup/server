package com.jerry.gamemarket.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/12/25 0025 15:50
 */
@Data
public class CommentVO {
    @JsonProperty("id")
    private Integer articleCommentId;
    @JsonProperty("uid")
    private String userId;
    @JsonProperty("uname")
    private String userName;
    @JsonProperty("text")
    private String commentText;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("dislikes")
    private Integer dislikes;
    @JsonProperty("articleId")
    private Integer articleId;
    @JsonProperty("time")
    private Timestamp updateTime;
    @JsonProperty("lname")
    private String lastUname;
    @JsonProperty("lid")
    private String lastUid;
    @JsonProperty("status")
    private Integer status=0;
}
