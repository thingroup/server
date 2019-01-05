package com.jerry.gamemarket.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 13:38
 */
@Data
public class CanArticleVO {
    @JsonProperty("id")
    private Integer caId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("imgsrc")
    private String img;
    @JsonProperty("text")
    private String text;
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
    @JsonProperty("status")
    private Integer status=0;
    @JsonProperty("canteenId")
    private String canteenId;
}
