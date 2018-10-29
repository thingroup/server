package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
* 订单详情DAO，数据资源库
* author by 李兆杰
* 2018-10-4
* */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String>{
    List<OrderDetail> findByOrderId(String orderId);
}
