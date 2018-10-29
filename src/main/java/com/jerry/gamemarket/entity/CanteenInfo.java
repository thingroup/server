package com.jerry.gamemarket.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
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
  private Double canteenAddress;
  private Integer canteenHallStock;
  private Integer canteenRoomStock;
  private String canteenDescription;
  private String canteenIcon;
  private String roomIcon;
  private String hallIcon;
//  private Date createTime;
//  private Date updateTime;
  private Integer canteenStatus;

}
