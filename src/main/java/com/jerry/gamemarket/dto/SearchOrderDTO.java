package com.jerry.gamemarket.dto;

import com.jerry.gamemarket.enums.OrderStatusEnums;
import com.jerry.gamemarket.enums.PayStatusEnums;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * author by 李兆杰
 * Date 2018/11/28
 */
@Data
public class SearchOrderDTO {

    private  String orderId;
    //   private String id;
    private  String buyerName;

    private  String buyerPhone;

    private  String buyerAddress;

    private  String canteenName;

    private BigDecimal maxAmount;
    private BigDecimal minAmount;

    private Integer orderStatus;
    //    默认未支付
    private Integer payStatus;

    private Integer pageNum = 1;

    private Integer pageSize=100;

}
