package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.CouponInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* 优惠券DAO，数据资源库
* author by 李兆杰
* 2018-10-7
* */

@Repository
public interface CouponDao extends JpaRepository<CouponInfo,String> {
    List<CouponInfo> findByCouponStatus(Integer couponStatus);
    List<CouponInfo> findByCanteenId(String canteenId);
    List<CouponInfo> findByProductId(String productId);
}
