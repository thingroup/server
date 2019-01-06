package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/11/30 0030 20:31
 */
@Data
public class ArticleDTO {
    private Integer articleId;
    private String articleName;
    private String articleImg;
    private String articleText;
    private String userId;
    private Timestamp updateTime;
    private Float score;
    private Integer likes;
    private Integer dislikes;
    private String userName;
    private List<String> productNames;
    private List<CommentDTO> commentList;
    private Integer role;
}
