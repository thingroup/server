package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired OrderDetailDao orderDetailDao;
    @Test
    public void SaveTest(){
        OrderDetail orderDetail =new OrderDetail();
        orderDetail.setDetailId("123456");
        orderDetail.setOrderId("11111");
        orderDetail.setProductIcon("dasd");
        orderDetail.setProductName("test");
        orderDetail.setProductPrice(new BigDecimal(50.2));
        orderDetail.setProductQuantity(50);
        orderDetail.setProductId("124554");
        OrderDetail result=orderDetailDao.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> orderDetailsList=orderDetailDao.findByOrderId("11111");
        Assert.assertNotEquals(0,orderDetailsList.size());
    }

}