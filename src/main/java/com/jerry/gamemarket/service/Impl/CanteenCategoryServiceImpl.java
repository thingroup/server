package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dao.CanteenCategoryDao;
import com.jerry.gamemarket.dao.ProductCategoryDao;
import com.jerry.gamemarket.entity.CanteenCategory;
import com.jerry.gamemarket.entity.ProductCategory;
import com.jerry.gamemarket.service.CanteenCategoryService;
import com.jerry.gamemarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 类目service实现类
* author by 李兆杰
* 2018-11-21
* */
@Service
public class CanteenCategoryServiceImpl implements CanteenCategoryService {
    @Autowired
    private CanteenCategoryDao canteenCategoryDao;


    @Override
    public CanteenCategory findOne(Integer categoryId) {
        return canteenCategoryDao.getOne(categoryId);
    }

    @Override
    public List<CanteenCategory> findAll() {
        return canteenCategoryDao.findAll();
    }

    @Override
    public List<CanteenCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return canteenCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public CanteenCategory save(CanteenCategory canteenCategory) {
        return canteenCategoryDao.save(canteenCategory);
    }
}
