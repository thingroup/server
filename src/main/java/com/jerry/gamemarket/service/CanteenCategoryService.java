package com.jerry.gamemarket.service;

import com.jerry.gamemarket.entity.CanteenCategory;
import com.jerry.gamemarket.entity.ProductCategory;

import java.util.List;

/*
* 类目service接口
* author by 李兆杰
* 2018-10-4
* */
public interface CanteenCategoryService {
//    查找单个类目
    CanteenCategory findOne(Integer categoryId);
//    查找类目列表
    List<CanteenCategory> findAll();
//    按类型查找
    List<CanteenCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
//    存储一个类目
    CanteenCategory save(CanteenCategory canteenCategory);
}
