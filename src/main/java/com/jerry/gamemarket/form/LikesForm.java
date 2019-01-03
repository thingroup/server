package com.jerry.gamemarket.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 叶俊晖
 * @date 2018/12/27 0027 14:37
 */
@Data
public class LikesForm {
    @NotNull(message = "原本状态不为空")
    private Integer status;
    @NotNull(message = "点赞类型不为空")
    private Integer type;
    @NotNull(message = "点评id不为空")
    private String articleId;
    private String articleCommentId;
    @NotNull(message = "餐厅id不为空")
    private String canteenId;
}
