package com.jerry.gamemarket.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 17:05
 */
@Data
public class CanCommentVo {
    @JsonProperty("id")
    private Integer canCommentId;
    @JsonProperty("uid")
    private String userId;
    @JsonProperty("uname")
    private String userName;
    @JsonProperty("text")
    private String text;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("dislikes")
    private Integer dislikes;
    @JsonProperty("caId")
    private Integer caId;
    @JsonProperty("time")
    private Timestamp updateTime;
    @JsonProperty("lname")
    private String lastUname;
    @JsonProperty("lid")
    private String lastUid;
    @JsonProperty("status")
    private Integer status=0;
}
