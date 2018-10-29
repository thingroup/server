package com.jerry.gamemarket.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/*
* 游戏商品信息
* author by 李兆杰
* 2018-10-4
* */
@Entity
@Data
@Proxy(lazy = false)
//动态更新时间
@DynamicUpdate
public class ProductInfo {
    @Id
//    产品id
    private String productId;
//    产品名称
    private String productName;
//    产品单价
    private BigDecimal productPrice;
//    产品库存
    private Integer productStock;
//    产品详情
    private String productDescription;
//    产品图标
    private String productIcon;
//    产品类目
    private Integer categoryType;
//    产品状态 0为正常出售，1为无货或者下架
    private int productStatus;
//    产品餐厅
    private String canteenId;

}
