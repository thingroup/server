package com.jerry.gamemarket.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.dto.SearchOrderDTO;
import com.jerry.gamemarket.dto.StatisticMonthDTO;
import com.jerry.gamemarket.dto.StatisticOrderDTO;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.entity.OrderMaster;
import com.jerry.gamemarket.enums.OrderStatusEnums;
import com.jerry.gamemarket.enums.PayStatusEnums;
import com.querydsl.core.QueryResults;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void dymamicQuery() throws Exception {
        SearchOrderDTO searchOrderDTO =new SearchOrderDTO();
        System.out.println(searchOrderDTO);
        searchOrderDTO.setMinAmount(new BigDecimal(100));
        searchOrderDTO.setMaxAmount(new BigDecimal(300));
        QueryResults<OrderMaster> queryResults = orderService.dymamicQuery(searchOrderDTO);
        System.out.println(objectMapper.writeValueAsString(queryResults));
    }

    @Test
    public void statisByMonth() throws Exception {
        List<StatisticMonthDTO> statisticMonthDTOS=orderService.statisByMonth();
        System.out.println(statisticMonthDTOS);
    }

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
        String list[] =new String[]{
                "marry","henry","bob","tom",
                "Mary","ALLen","Olivia","Kevin",
                "Rose","Kelly","Jeanne","James",
                "Edith","Sophia","Charles","Ashley",
                "jack","Vera","john","Tracy",
                "Grace","Ruth","Rebert","Emma",
                "Grace","Ruth","Rebert","Emma",
                "Grace","Ruth","Rebert","Emma",
                "Grace","Ruth","Rebert","Emma",
                "Grace","Ruth","Rebert","Emma",
                "Edith","Sophia","Charles","Ashley",
                "Edith","Sophia","Charles","Ashley",
                "Edith","Sophia","Charles","Ashley",
                "Edith","Sophia","Charles","Ashley",
                "Edith","Sophia","Charles","Ashley",};
        String list2[] = new String[]{
                "花园大酒店","索菲亚大酒店","皇嘉华园",
                "艾瑞莉娅","花园大酒店","索菲亚大酒店"
                ,"皇嘉华园","恕瑞玛","花园大酒店",
                "索菲亚大酒店","皇嘉华园","德玛西亚"
                ,"花园大酒店","索菲亚大酒店","皇嘉华园",
                "弗雷尔卓德","花园大酒店","索菲亚大酒店",
                "皇嘉华园","无畏先锋","巴黎铁塔",
                "暗影玫瑰","皇嘉华园","无畏先锋",
                "巴黎铁塔","暗影玫瑰","比尔吉沃特",
                "暗影玫瑰","皇嘉华园","无畏先锋",
                "巴黎铁塔","暗影玫瑰","比尔吉沃特",
                "暗影玫瑰","皇嘉华园","无畏先锋",
                "巴黎铁塔","暗影玫瑰","比尔吉沃特",
                "暗影玫瑰","皇嘉华园","无畏先锋",
                "巴黎铁塔","暗影玫瑰","比尔吉沃特",
                "花园大酒店","索菲亚大酒店","皇嘉华园",
                "艾瑞莉娅","花园大酒店","索菲亚大酒店",
                "花园大酒店","索菲亚大酒店","皇嘉华园",
                "艾瑞莉娅","花园大酒店","索菲亚大酒店",
                "巴黎铁塔","暗影玫瑰","比尔吉沃特"
        };
                String list3[] = new String[]{
                 "17898745897","17854788554","13578458974",
                "17845878956","13785236945","15878458877",
                 "15054788955","17788445588","13578965478","17854789658",
                        "17898745897","17854788554","13578458974",
                        "17845878956","13785236945","15878458877",
                        "15054788955","17788445588","13578965478","17854789658",
                        "17898745897","17854788554","13578458974",
                        "17845878956","13785236945","15878458877",
                        "15054788955","17788445588","13578965478","17854789658",
                        "17898745897","17854788554","13578458974",
                        "17845878956","13785236945","15878458877",
                        "15054788955","17788445588","13578965478","17854789658",
                        "17898745897","17854788554","13578458974",
                        "17845878956","13785236945","15878458877",
                        "15054788955","17788445588","13578965478","17854789658",
                        "17898745897","17854788554","13578458974",
                        "17845878956","13785236945","15878458877",
                        "15054788955","17788445588","13578965478","17854789658",};
                String list4[] = new String[]{
                        "dkjsadasd","eedadwwww","dwad21wdd","dsadwdwad","dghjgjhghgf",
                        "ytyuyeuur","dadwuuuur","dwriiiiiiw","deuuuurrr","dsfsdfrttt",
                        "dkjsadasd","eedadwwww","dwad21wdd","dsadwdwad","dghjgjhghgf",
                        "ytyuyeuur","dadwuuuur","dwriiiiiiw","deuuuurrr","dsfsdfrttt",
                        "dkjsadasd","eedadwwww","dwad21wdd","dsadwdwad","dghjgjhghgf",
                        "ytyuyeuur","dadwuuuur","dwriiiiiiw","deuuuurrr","dsfsdfrttt",
                        "dkjsadasd","eedadwwww","dwad21wdd","dsadwdwad","dghjgjhghgf",
                        "ytyuyeuur","dadwuuuur","dwriiiiiiw","deuuuurrr","dsfsdfrttt",
                        "dkjsadasd","eedadwwww","dwad21wdd","dsadwdwad","dghjgjhghgf",
                        "ytyuyeuur","dadwuuuur","dwriiiiiiw","deuuuurrr","dsfsdfrttt",
                        "dkjsadasd","eedadwwww","dwad21wdd","dsadwdwad","dghjgjhghgf",
                        "ytyuyeuur","dadwuuuur","dwriiiiiiw","deuuuurrr","dsfsdfrttt"
                };
                String list5[] = new String[]{
                "124512","124512","124512",
                "124512","124512","124512",
                "124512","154212","114477",
                "224478","224478","224478",
                "224478","154212","114477",
                "114488","224478","448779",
                "124512","154212","114477",
                "114488","224478","448779",
                "124512","154212","114477",
                "114488","224478","448779",
                "124512","154212","114477",
                "114488","224478","448779",
                 "124512","154212","114477",
                "114488","224478","448779",
                        "124512","154212","114477",
                        "114488","224478","448779",
                        "124512","154212","114477",
                        "114488","224478","448779",
                        "124512","154212","114477",
                        "114488","224478","448779",

                };
                String list6[] = new String[]{
                       "深夜小馆","深夜小馆","深夜小馆",
                       "深夜小馆","深夜小馆","深夜小馆",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "小城味道","小城味道","小城味道",
                        "小城味道","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
                        "深夜小馆","聚朋小厨","杰瑞厨房",
                        "美味佛山","小城味道","青岛后海",
        };
        String list7[] = new String[]{"12315","12315","124554","124554","12315","12315",
                "12315","1551215","1144477","124554","12315","12315",
                "12315","1551215","1144477","124554","12315","12315",
                "12315","1551215","1144477","124554","12315","12315",
                "12315","1551215","148454","124554","12315","12315",
                "12315","1551215","1144477","124554","12315","12315",
                "12315","1551215","1144477","124554","148454","12315",
                "148454","1551215","148454","124554","12315","12315",
                "12315","1551215","1144477","124554","12315","12315",
                "12315","1551215","1144477","124554","12315","12315",

        };
                for (int i = 0; i < list.length; i++) {
            OrderDTO orderDTO= new OrderDTO();
            orderDTO.setBuyerName(list[i]);
            orderDTO.setBuyerAddress(list2[i]);
            orderDTO.setBuyerPhone(list3[i]);
            orderDTO.setBuyerOpenid(list4[i]);
            orderDTO.setCanteenId(list5[i]);
            orderDTO.setCanteenName(list6[i]);
            List<OrderDetail> orderDetailList=new ArrayList<>();

            OrderDetail ol = new OrderDetail();
            ol.setProductId(list7[i]);
            ol.setProductQuantity(3);
            orderDetailList.add(ol);
            orderDTO.setOrderDetailList(orderDetailList);

            OrderDTO result = orderService.create(orderDTO);
            log.info("【创建订单】 result=",result);
            Assert.assertNotNull(result);
        }

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