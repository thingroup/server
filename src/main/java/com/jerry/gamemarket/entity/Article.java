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
 * @date 2018/11/30 0030 20:28
 */
@Data
@Entity
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
public class Article {
    //   设置主键
    @Id
//   自动更新生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;
    private String articleName;
    private String articleImg;
    private String orderId;
    private String canteenId;
    private String articleText;
    private String userId;
    private Timestamp updateTime;
    private Float score;
    private Integer likes;
    private Integer dislikes;
    private String userName;
    private Integer role;
}
