package com.jerry.gamemarket.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* 类目实体类
* author by 李兆杰
* 2018-10-4
* */


//添加实体类注解
@Entity
//动态更新时间
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
@Data
public class CanteenCategory {

//   设置主键
    @Id
//   自动更新生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
//    类目名称
    private String categoryName;
//    类目编号
    private Integer categoryType;
////    更新时间
//    private Date updateTime;
////    创建时间
//    private Date createTime;


    public CanteenCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;

    }

    public CanteenCategory() {
    }
}
