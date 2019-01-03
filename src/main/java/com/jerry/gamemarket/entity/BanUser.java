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
 * @date 2019/1/2 0002 16:11
 */
@Data
@Entity
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
public class BanUser {
    //   设置主键
    @Id
//   自动更新生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer banId;
    private String userId;
    private String userName;
    private Timestamp updateTime;
    private String reason;
}
