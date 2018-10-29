package com.jerry.gamemarket.service;

import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 游戏商品信息
* author by 李兆杰
* 2018-10-4
* */

public interface ProductService {
//    查找单个游戏商品
    ProductInfo findOne(String productId);
//    查询所有可以售卖的游戏商品
    List<ProductInfo> findUpAll();
//    查询所有商品
    Page<ProductInfo> findAll(Pageable pageable);
//存储产品信息
    ProductInfo save(ProductInfo productInfo);

//    进货 加库存
    void increaseStock(List<CartDTO> cartDTOList);
//    售出 减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
