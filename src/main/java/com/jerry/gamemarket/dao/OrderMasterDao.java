package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/*
* 订单主表DAO，数据资源库
* author by 李兆杰
* 2018-10-4
* */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String>{
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
