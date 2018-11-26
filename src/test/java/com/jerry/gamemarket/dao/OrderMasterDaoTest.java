package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.dto.StatisticOrderDTO;
import com.jerry.gamemarket.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
    @Test
    public void statisOrderCountByMonth() throws Exception {
        List<?> result =orderMasterDao.StatisOrderCountByMonth(2018);
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<result.size();i++){
            Object[] obj = (Object[])result.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("month", obj[0]);
            map.put("orderCount", obj[1]);
            list.add(map);
        }
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
//    @Transactional
//    @Test
//    public void deleteByBuyerNameAndCanteenId() throws Exception {
//        orderMasterDao.deleteByBuyerNameAndCanteenId("李兆杰","124512");
//    }

    @Test
    public void findByCanteenId() throws Exception {
        List<?> result =orderMasterDao.StatisOrderCount();
        List<Map> list=new ArrayList<Map>();
        for(int i=0;i<result.size();i++){
            Object[] obj = (Object[])result.get(i);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("canteenId", obj[0]);
            map.put("orderCount", obj[1]);
            list.add(map);
        }
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void findtest() throws Exception {
        List<OrderMaster> result =orderMasterDao.findtest("jerry","1542800544788101397");
        System.out.println(result);
    }

    @Autowired
    private OrderMasterDao orderMasterDao;
    private final String OPENID="jerry";
    @Test
    public void saveTest(){
        OrderMaster orderMaster =new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("叶俊辉");
        orderMaster.setBuyerPhone("17865428032");
        orderMaster.setBuyerAddress("青岛市浮山公寓");
        orderMaster.setBuyerOpenid("Franklin");
        orderMaster.setOrderAmount(new BigDecimal(8.5));
        OrderMaster result=orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0,1);
        Page<OrderMaster> result = orderMasterDao.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
        System.out.println(result.getTotalElements());

    }

}