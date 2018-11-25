package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.dto.StatisticOrderDTO;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.enums.OrderStatusEnums;
import com.jerry.gamemarket.enums.PayStatusEnums;
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

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Test
    public void statis() throws Exception {
        List<StatisticOrderDTO> statisticOrderDTOS=orderService.statis();
        System.out.println(statisticOrderDTOS);
    }
//    @Test
//    public void statis() throws Exception {
//        List<StatisticOrderDTO> statisticOrderDTOS=orderService.statis();
//        System.out.println(statisticOrderDTOS);
//    }

    @Test
    public void findByCase() throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage=orderService.findByCase("order_id","1508001584066370209",request);
        log.info("查询单个订单result={}", orderDTOPage);
    }

    private  final String BUYER_OPENID="15131215";
    @Autowired
    private OrderServiceImpl orderService;
    final static String ORDER_ID = "1508001584066370209";
    @Test
    public void create() throws Exception {
        OrderDTO orderDTO= new OrderDTO();
        orderDTO.setBuyerName("jerry");
        orderDTO.setBuyerAddress("浮山公寓");
        orderDTO.setBuyerPhone("17865428032");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail ol = new OrderDetail();
        ol.setProductId("1551215");
        ol.setProductQuantity(3);
        orderDetailList.add(ol);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result=",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("查询单个订单result={}",result);
        Assert.assertNotNull(ORDER_ID,result.getOrderId());
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
    Assert.assertEquals(OrderStatusEnums.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    OrderDTO result=orderService.finish(orderDTO);
    Assert.assertEquals(OrderStatusEnums.FINISH.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    OrderDTO result=orderService.paid(orderDTO);
    Assert.assertEquals(PayStatusEnums.FINISH.getCode(),result.getPayStatus());
    }
    @Test
    public void list() throws Exception{
        PageRequest pageRequest = new PageRequest(0 , 2);
        Page<OrderDTO> orderDTOS = orderService.findList(pageRequest);
        Assert.assertTrue("卖家获取订单列表" , orderDTOS.getContent().size() > 0);
    }

}