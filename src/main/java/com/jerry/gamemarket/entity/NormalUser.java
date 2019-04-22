package com.jerry.gamemarket.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @author 叶俊晖
 * @date 2019/3/19 0019 23:23
 */
@Data
@Entity
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
public class NormalUser {
    //   设置主键
    @Id
//   自动更新生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userPass;
    private String userPhone;
    private String userGender;
    private String wechatId;
    private String userIcon;
    private Integer userRole;
    private Date userBirth;
    private String userEmail;
}
