package com.jerry.gamemarket.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 14:49
 */
@Data
public class CreateComment {
    private String userId;
    private String userName;
    @NotNull(message = "内容不能为空")
    private String commentText;
    @NotNull(message = "父评论ID不能为空")
    private Integer articleId;
    private String lastUname;
    private String lastUid;
    private Integer role=0;
}
