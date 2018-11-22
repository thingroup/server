package com.jerry.gamemarket.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
public class CouponInfo {
  @Id
  private String couponId;
  private String couponName;
  private String productName;
  private Integer couponStock;
  private String couponDescription;
  private String couponIcon;
//  折扣
  private BigDecimal couponDiscount;
//  满减金额
  private BigDecimal reductionMoney;
//  通用红包
  private BigDecimal couponRedpacket;
//  需要的满减要求
  private BigDecimal requireMoney;
  private Date createTime;
  private Date updateTime;
  private Date deadline;
  private Date couponStatus;
  private String canteenId;
  private String productId;
  }
