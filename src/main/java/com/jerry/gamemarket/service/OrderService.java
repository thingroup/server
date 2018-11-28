package com.jerry.gamemarket.service;

import com.alibaba.fastjson.JSONArray;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.dto.SearchOrderDTO;
import com.jerry.gamemarket.dto.StatisticMonthDTO;
import com.jerry.gamemarket.dto.StatisticOrderDTO;
import com.jerry.gamemarket.entity.OrderMaster;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface OrderService {
//    创建订单
    OrderDTO create(OrderDTO orderDTO);
//    查询单个订单
    OrderDTO findOne(String orderId);
//    查询订单列表
    Page<OrderDTO> findList(String buyerOpenid,Pageable pageable);
//    取消订单
    OrderDTO cancel(OrderDTO orderDTO);
//    完结订单
    OrderDTO finish(OrderDTO orderDTO);
//    支付订单
    OrderDTO paid(OrderDTO orderDTO);
    /**查找订单.**/
    Page<OrderDTO> findList(Pageable pageable);
//  多条件查询
//    Page<OrderDTO> findbyNameAndCanteenId(String buyerName,String canteenId,Pageable pageable);
//  可选条件查询
    Page<OrderDTO> findByCase(String tip,String text,Pageable pageable);

//    统计店铺订单数量
   List<StatisticOrderDTO> statis();
//   统计月份订单数量
    List<StatisticMonthDTO> statisByMonth();

//    动态查询
   QueryResults<OrderMaster> dymamicQuery(SearchOrderDTO searchOrderDTO);
}
