package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.VO.ProductInfoVO;
import com.jerry.gamemarket.VO.ProductVO;
import com.jerry.gamemarket.VO.ResultVO;
import com.jerry.gamemarket.entity.ProductCategory;
import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.service.CategoryService;
import com.jerry.gamemarket.service.ProductService;
import com.jerry.gamemarket.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/*
* 买家产品Controller
* author by 李兆杰
* 2018-10-4
* */
@CrossOrigin
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
//        查询所有的上架产品
        List<ProductInfo> productInfoList = productService.findUpAll();
//        查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
//        传统方法for循环
//        for(ProductInfo productInfo : productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
//        lambda表达式
        List<Integer> categoryTypeList=productInfoList.stream()
                .map(e ->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList= categoryService.findByCategoryTypeIn(categoryTypeList);

//        数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory: productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo: productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);

        }
        return ResultVOUtil.success(productVOList);
    }
}

