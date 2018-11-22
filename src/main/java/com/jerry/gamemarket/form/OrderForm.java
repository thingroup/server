package com.jerry.gamemarket.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
@Data
public class OrderForm {
//    买家姓名
    @NotEmpty(message = "姓名必填")
    private String name;
//    买家手机号
    @NotEmpty(message = "手机号必填")
    private String phone;
//    买家通讯地址必填
    @NotEmpty(message = "通讯地址必填")
    private String address;
//    买家微信id
    @NotEmpty(message = "openid必填")
    private String openid;
//    购物车
    @NotEmpty(message = "购物车必填")
    private String items;
////    预订要来的人数必填
//    @NotEmpty(message = "预订人数必填")
//    private Integer peopleNum;
//    预订包间数量
    @NotEmpty(message = "预订包间数必填")
    private String roomQuantity;

//    @NotEmpty(message = "预约时间必填")
//    private Date appointmentTime;
////    备注
//    private String comment;
}
