package com.jerry.gamemarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.enums.OrderStatusEnums;
import com.jerry.gamemarket.enums.PayStatusEnums;
import com.jerry.gamemarket.utils.serializer.Date2LongSerializer;
import com.jerry.gamemarket.utils.serializer.EnumsUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 订单DTO
 * author by 李兆杰
 * 2018-10-7
 * */
@Data
public class OrderDTO {
    private  String orderId;
//    private String canteenId;
    private  String buyerName;

    private  String buyerPhone;

    private  String buyerAddress;

    public OrderDTO() {
    }

    private  String buyerOpenid;

    private BigDecimal orderAmount;
//    private BigDecimal couponAmount;
    //    默认新订单
    private Integer orderStatus= OrderStatusEnums.NEW.getCode();
    //    默认未支付
    private Integer payStatus= PayStatusEnums.WAIT.getCode();
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

//    private Integer roomQuantity;
//
//    private Integer peopleNum;
//
//    private String comment;
//    @JsonSerialize(using = Date2LongSerializer.class)
//
//    private Date appointmentTime;
//
//    public OrderDTO(Integer roomQuantity) {
//        this.roomQuantity = roomQuantity;
//    }

    private List<OrderDetail> orderDetailList;


//
//    public OrderStatusEnums getOrderStatusEnum() {
//        return EnumsUtils.getEnumsByCode(orderStatus , OrderStatusEnums.class);
//    }
//
//
//    public PayStatusEnums getPayStatusEnum() {
//        return EnumsUtils.getEnumsByCode(payStatus , PayStatusEnums.class);
//    }
}
