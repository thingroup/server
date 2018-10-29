package com.jerry.gamemarket.entity;

import com.jerry.gamemarket.enums.OrderStatusEnum;
import com.jerry.gamemarket.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicUpdate
//设置懒加载为false
@Proxy(lazy = false)
public class OrderMaster {
    @Id
    private  String orderId;

    private  String buyerName;

    private  String buyerPhone;

    private  String buyerAddress;

    private  String buyerOpenid;

    private BigDecimal orderAmount;
//    默认新订单
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
//    默认未支付
    private Integer payStatus= PayStatusEnum.WAIT.getCode();
//    优惠的金额
    private BigDecimal couponAmount;
//    餐厅Id
    private String canteenId;

    private Date createTime;

    private Date updateTime;
//    预约人数
    private Integer peopleNum;
//    预约到店时间
    private Date appointmentTime;

    private String comment;

}
