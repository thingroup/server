package com.jerry.gamemarket.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.enums.OrderStatusEnum;
import com.jerry.gamemarket.enums.PayStatusEnum;
import com.jerry.gamemarket.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private String canteenId;
    private  String buyerName;

    private  String buyerPhone;

    private  String buyerAddress;

    public OrderDTO() {
    }

    private  String buyerOpenid;

    private BigDecimal orderAmount;
    private BigDecimal couponAmount;
    //    默认新订单
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    //    默认未支付
    private Integer payStatus= PayStatusEnum.WAIT.getCode();
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private Integer roomQuantity;

    private Integer peopleNum;

    private String comment;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date appointmentTime;
    public OrderDTO(Integer roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    private List<OrderDetail> orderDetailList;

}
