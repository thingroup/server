package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dao.CouponDao;
import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.entity.CouponInfo;
import com.jerry.gamemarket.enums.CouponStatusEnum;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponDao couponDao;
    @Override
    public CouponInfo findOne(String couponId) {
        return couponDao.getOne(couponId);
    }

    @Override
    public List<CouponInfo> findUpAll() {

        return couponDao.findByCouponStatus(CouponStatusEnum.In.getCode());
    }
//分页查询
    @Override
    public Page<CouponInfo> findAll(Pageable pageable) {
        return couponDao.findAll(pageable);
    }

    @Override
    public CouponInfo save(CouponInfo couponInfo) {
        return couponDao.save(couponInfo);
    }

    @Override
    public List<CouponInfo> findByProductId(String productId) {
        couponDao.findByProductId(productId);
        return null;
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
     for(CartDTO cartDTO : cartDTOList){
         CouponInfo couponInfo = couponDao.getOne(cartDTO.getProductId());
         if(couponInfo ==null){
             throw  new GameException(ResultEnum.COUPON_NOT_EXIST);
         }
         Integer result = couponInfo.getCouponStock()-cartDTO.getCouponQuantity();
         if(result<0){
             throw  new GameException(ResultEnum.COUPON_NOT_ENOUGH);
         }
         couponInfo.setCouponStock(result);
         couponDao.save(couponInfo);
     }
    }
}
