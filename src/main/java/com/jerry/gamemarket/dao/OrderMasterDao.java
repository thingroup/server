package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.dto.StatisticOrderDTO;
import com.jerry.gamemarket.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
* 订单主表DAO，数据资源库
* author by 李兆杰
* 2018-10-4
* */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String>{
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    @Query("select  u.buyerName from  OrderMaster u where u.buyerName like CONCAT('%',:buyerName,'%') ")
    List<OrderMaster> findByBuyerNameLike(@Param("buyerName") String buyerName);

//    @Query(value = "select * from OrderMaster as u where u.buyerName=?1 and u.canteenId=?2",nativeQuery = true)
//    Page<OrderDTO> findByNameAndCanteenId(String buyerName, String canteenId,Pageable pageable);
//
//    @Query(value = "select * from OrderMaster as u where u.orderId=?1",nativeQuery = true)
//    List<OrderMaster> findByOrderId(String orderId);

    @Query(value = "select * from order_master  where ?1 = ?2",nativeQuery = true)
    Page<OrderDTO> findByCase(String tip,String text,Pageable pageable);

    @Query(value = "select * from order_master  where ?1 =?2",nativeQuery = true)
    List<OrderMaster> findtest( String tip,String text);

    @Query(value = "SELECT canteen_id,COUNT(canteen_id) as order_num ,canteen_name FROM order_master GROUP BY canteen_id ",nativeQuery = true)
    List<String> StatisOrderCount();

    @Query(value = "SELECT MONTH(create_time),COUNT(*) AS num FROM order_master where YEAR(create_time)=?1 GROUP BY MONTH(create_time) asc",nativeQuery = true)
    List<String> StatisOrderCountByMonth(Integer year);




}
