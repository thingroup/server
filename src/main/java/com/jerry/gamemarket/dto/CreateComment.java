package com.jerry.gamemarket.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 14:49
 */
@Data
public class CreateComment {
    private String userId;
    private String userName;
    private String commentText;
    private Integer articleId;
    private String lastUname;
    private String lastUid;
}
