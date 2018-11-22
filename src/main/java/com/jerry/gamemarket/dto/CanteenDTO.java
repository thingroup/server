package com.jerry.gamemarket.dto;

/**
 * author by 李兆杰
 * Date 2018/11/22
 */
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.utils.serializer.Date2LongSerializer;
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
public class CanteenDTO {
//    餐厅id
    private String canteenId;
//    餐厅名称
    private String canteenName;
//    餐厅地址
    private String canteenAddress;
//    包间数量
    private Integer canteenRoomStock;
//    联系方式
    private String canteenPhone;
//    餐厅描述
    private String canteenDescription;
    public CanteenDTO() {
    }

    //    商品列表
    private List<ProductInfo> productInfoList;
//    餐厅状态
    private Integer canteenStatus;
//    创建时间
//    @JsonSerialize(using = Date2LongSerializer.class)
//    private Date createTime;
}
