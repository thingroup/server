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
 * @date 2018/12/31 0031 15:35
 */
@Data
@Entity
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
public class CanteenArticle {
    //   设置主键
    @Id
//   自动更新生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caId;
    private String canteenId;
    private String userId;
    private String userName;
    private Integer likes;
    private Integer dislikes;
    private Integer role;
    private Timestamp updateTime;
    private String img;
    private String text;
}
