package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.enums.OrderStatusEnum;
import com.jerry.gamemarket.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    private  final String BUYER_OPENID="1111244";
    @Autowired
    private OrderServiceImpl orderService;
    private final String ORDER_ID="123";
    @Test
    public void create() throws Exception {
        OrderDTO orderDTO= new OrderDTO();
        orderDTO.setBuyerName("jerry");
        orderDTO.setBuyerAddress("浮山公寓");
        orderDTO.setBuyerPhone("17865428032");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        List<OrderDetail> orderDetailLsit=new ArrayList<>();

        OrderDetail ol = new OrderDetail();
        ol.setProductId("1354856");
        ol.setProductQuantity(3);
        orderDetailLsit.add(ol);
        orderDTO.setOrderDetailList(orderDetailLsit);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result=",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("查询单个订单result={}",result);
        Assert.assertNotEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    OrderDTO result=orderService.cancel(orderDTO);
    Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    OrderDTO result=orderService.finish(orderDTO);
    Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    OrderDTO result=orderService.paid(orderDTO);
    Assert.assertEquals(PayStatusEnum.FINISHED.getCode(),result.getPayStatus());
    }

}