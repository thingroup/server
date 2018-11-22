package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.dao.CanteenDao;
import com.jerry.gamemarket.dto.CanteenDTO;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.CanteenCategory;
import com.jerry.gamemarket.entity.CanteenInfo;
import com.jerry.gamemarket.entity.ProductCategory;
import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.form.CanteenForm;
import com.jerry.gamemarket.form.ProductForm;
import com.jerry.gamemarket.service.CanteenCategoryService;
import com.jerry.gamemarket.service.CanteenService;
import com.jerry.gamemarket.service.OrderService;
import com.jerry.gamemarket.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * Created by 李兆杰 on 2018/11/21 0001.
 */
@Controller
@Slf4j
@RequestMapping("/seller/canteen")
public class SellerCanteenController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CanteenService canteenService;
    @Autowired
    private CanteenDao canteenDao;
    @Autowired
    private CanteenCategoryService canteenCategoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(required = false , value = "page" , defaultValue = "1") Integer page,
                             @RequestParam(required = false , value = "size" , defaultValue = "10") Integer size,
                             Map<String ,Object> map) {

        PageRequest pageRequest = new PageRequest(page -1 , size);
        Page<CanteenDTO> canteenDTOS = canteenService.findList(pageRequest);
        map.put("canteenDTOS" , canteenDTOS);
        map.put("currentPage" , page);
        map.put("size" , size);
        return new ModelAndView("shops/list" , map);

    }

    @GetMapping("/goods")
    public ModelAndView detail(@RequestParam(required = false,value="canteenId",defaultValue = "124512")String canteenId,
                               @RequestParam(required = false , value = "page" , defaultValue = "1") Integer page,
                               @RequestParam(required = false , value = "size" , defaultValue = "10") Integer size,
                               Map<String,Object> map) {
        CanteenDTO canteenDTOS = null;
        try {
            canteenDTOS = canteenService.findOne(canteenId);
        }catch (GameException e) {
            log.error("【卖家商品查询】发生错误，e={}" , e);
            map.put("msg" , e.getMessage());
            map.put("url" , "list");
            return new ModelAndView("/common/error" , map);
        }
        map.put("currentPage" , page);
        map.put("canteenDTO" , canteenDTOS);
        return new ModelAndView("shops/goods" , map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "canteenId", required = false) String canteenId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(canteenId)) {
            CanteenInfo canteenInfo = canteenDao.getOne(canteenId);
            map.put("canteenInfo", canteenInfo);
        }

        //查询所有的类目
        List<CanteenCategory> categoryList = canteenCategoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("shops/index", map);
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid CanteenForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/canteen/index");
            return new ModelAndView("common/error", map);
        }

        CanteenInfo canteenInfo = new CanteenInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getCanteenId())) {
                canteenInfo = canteenDao.getOne(form.getCanteenId());
            } else {
                form.setCanteenId(KeyUtil.genUniquekey());
            }
            BeanUtils.copyProperties(form, canteenInfo);
            canteenService.save(canteenInfo);
        } catch (GameException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/canteen/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/canteen/list");
        return new ModelAndView("common/success", map);
    }
}
