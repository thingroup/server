package com.jerry.gamemarket.service;
import com.jerry.gamemarket.entity.ProductCategory;
import java.util.List;
/*
* 类目service接口
* author by 李兆杰
* 2018-10-4
* */
public interface CategoryService {
//    查找单个产品
    ProductCategory findOne(Integer categoryId);
//    查找产品列表
    List<ProductCategory> findAll();
//    按类型查找
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
//    存储一个类目
    ProductCategory save(ProductCategory productCategory);
}
