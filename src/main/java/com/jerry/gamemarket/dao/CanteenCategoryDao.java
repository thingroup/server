package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.CanteenCategory;
import com.jerry.gamemarket.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
* 类目DAO，数据资源库
* author by 李兆杰
* 2018-10-4
* */

public interface CanteenCategoryDao extends JpaRepository<CanteenCategory,Integer>{
    List<CanteenCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
