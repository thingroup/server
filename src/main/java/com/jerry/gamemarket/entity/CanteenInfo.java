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
public class CanteenInfo {
  @Id
  private String canteenId;
  private String canteenName;
  private String canteenAddress;
  private Integer canteenHallStock;
  private Integer canteenRoomStock;
  private String canteenDescription;
  private String canteenIcon;
  private String roomIcon;
  private String hallIcon;

//  餐厅类型
  private Integer categoryType;
//  纬度
  private double latitude;
//  经度
  private double longitude;
//  人均消费
  private BigDecimal personConsume;
//  手机号
  private String canteenPhone;
//  星级评价
  private Double Star;
//  热度值
  private Double hotCount;
//  private Date createTime;
//  private Date updateTime;

//  餐厅状态0为正常营业，1为打烊
  private Integer canteenStatus;

}
