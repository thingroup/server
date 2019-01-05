package com.jerry.gamemarket.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 14:32
 */
@Data
public class CreateArticle {
    private String orderId;
    @NotNull(message = "餐厅ID不能为空")
    private String canteenId;
    @NotNull(message = "评论名称不能为空")
    private String articleName;
    private String articleImg;
    @NotNull(message = "评论内容不能为空")
    private String articleText;
    private String userId;
    private String userName;
    @NotNull(message = "订单评分不能为空")
    private Float score;
}
