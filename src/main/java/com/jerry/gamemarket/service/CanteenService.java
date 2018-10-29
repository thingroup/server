package com.jerry.gamemarket.service;

import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.CanteenInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
public interface CanteenService {
    //    查找餐厅
    CanteenInfo findOne(String canteenId);
    //    查询所有开业的餐厅
    List<CanteenInfo> findUpAll();
    //    查询所有餐厅
    Page<CanteenInfo> findAll(Pageable pageable);
    //    存储餐厅信息
    CanteenInfo save(CanteenInfo canteenInfo);

    //    客人入座 减包间数量
    void increaseStock(String canteenId,Integer roomQuantity);
    //    客人离开 加包间数量
    void decreaseStock(String canteenId,Integer roomQuantity);
}
