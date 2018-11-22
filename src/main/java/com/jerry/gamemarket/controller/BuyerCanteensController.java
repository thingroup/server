package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.VO.*;
import com.jerry.gamemarket.dto.CanteenDTO;
import com.jerry.gamemarket.entity.CanteenCategory;
import com.jerry.gamemarket.entity.CanteenInfo;
import com.jerry.gamemarket.entity.ProductCategory;
import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.CanteenCategoryService;
import com.jerry.gamemarket.service.CanteenService;
import com.jerry.gamemarket.service.CategoryService;
import com.jerry.gamemarket.service.ProductService;
import com.jerry.gamemarket.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/*
* 买家产品Controller
* author by 李兆杰
* 2018-10-4
* */
@CrossOrigin
@RestController
@RequestMapping("/canteens")
public class BuyerCanteensController {
    @Autowired
    private CanteenService canteenService;
    @Autowired
    private CanteenCategoryService canteenCategoryService;
    @GetMapping("/list")
    public ResultVO list(){
//        查询所有的营业的店铺
        List<CanteenInfo> canteenInfoList = canteenService.findUpAll();
//        查询类目（一次性查询）
        System.out.println(canteenInfoList);
        List<Integer> categoryTypeList=canteenInfoList.stream()
                .map(e ->e.getCategoryType())
                .collect(Collectors.toList());
        List<CanteenCategory> categoryCategoryList= canteenCategoryService.findByCategoryTypeIn(categoryTypeList);

//        数据拼装
        List<CanteenVO> canteenVOList = new ArrayList<>();
        for(CanteenCategory canteenCategory: categoryCategoryList){
            CanteenVO canteenVO = new CanteenVO();
            canteenVO.setCategoryType(canteenCategory.getCategoryType());
            canteenVO.setCategoryName(canteenCategory.getCategoryName());


            List<CanteenInfoVO> canteenInfoVOList = new ArrayList<>();

            for (CanteenInfo canteenInfo: canteenInfoList){
                if(canteenInfo.getCategoryType().equals(canteenCategory.getCategoryType())){
                    CanteenInfoVO canteenInfoVO = new CanteenInfoVO();
                    BeanUtils.copyProperties(canteenInfo,canteenInfoVO);
                    canteenInfoVOList.add(canteenInfoVO);
                }
            }
            canteenVO.setCanteenInfoVOList(canteenInfoVOList);
            canteenVOList.add(canteenVO);

        }
        return ResultVOUtil.success(canteenVOList);
    }
    @GetMapping("/product")
    public ResultVO prolist(@RequestParam("canteenId")String canteenId){
        CanteenDTO canteenDTO = null;
        try {
            canteenDTO = canteenService.findOne(canteenId);
        }catch (GameException e) {
            return null;
        }
        return ResultVOUtil.success(canteenDTO);
    }
}

