package com.jerry.gamemarket.service;

import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.entity.CouponInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 游戏商品信息
* author by 李兆杰
* 2018-10-4
* */
@Service
public interface CouponService {
//    查找单个优惠券
    CouponInfo findOne(String couponId);
//    查询所有可以用的优惠券
    List<CouponInfo> findUpAll();
//    查询所有优惠券
    Page<CouponInfo> findAll(Pageable pageable);
//    存储优惠券信息
    CouponInfo save(CouponInfo productInfo);
//    按照产品id查找优惠券
    List<CouponInfo> findByProductId(String ProductId);

//    进货 加库存
    void increaseStock(List<CartDTO> cartDTOList);
//    售出 减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
