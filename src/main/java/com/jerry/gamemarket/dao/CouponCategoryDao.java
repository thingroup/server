package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.CouponCategory;
import com.jerry.gamemarket.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
* 优惠券类目DAO，数据资源库
* author by 李兆杰
* 2018-10-7
* */

public interface CouponCategoryDao extends JpaRepository<CouponCategory,Integer>{
    List<CouponCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
