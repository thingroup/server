package com.jerry.gamemarket.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/12/25 0025 14:15
 */
@Data
public class ArticleVO {
    @JsonProperty("id")
    private Integer articleId;
    @JsonProperty("name")
    private String articleName;
    @JsonProperty("imgsrc")
    private String articleImg;
    @JsonProperty("text")
    private String articleText;
    @JsonProperty("uid")
    private String userId;
    @JsonProperty("time")
    private Timestamp updateTime;
    @JsonProperty("score")
    private Float score;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("dislikes")
    private Integer dislikes;
    @JsonProperty("uname")
    private String userName;
    @JsonProperty("productNames")
    private List<String> productNames;
    @JsonProperty("comments")
    private List<CommentVO> commentList;
    @JsonProperty("status")
    private Integer status=0;
}
