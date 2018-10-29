package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
* 类目DAO，数据资源库
* author by 李兆杰
* 2018-10-4
* */

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
