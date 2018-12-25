package com.jerry.gamemarket.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 15:07
 */
@Data
@Entity
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
public class LikeHistory {
    //   设置主键
    @Id
//   自动更新生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;
    private String userId;
    private Integer type;
    private Timestamp updateTime;
    private String articleId;
    private String articleCommentId;
}
