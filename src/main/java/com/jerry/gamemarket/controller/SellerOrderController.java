package com.jerry.gamemarket.controller;

import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端商品
 * Created by 李兆杰 on 2018/11/21 0001.
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(required = false , value = "page" , defaultValue = "1") Integer page,
                             @RequestParam(required = false , value = "size" , defaultValue = "10") Integer size,
                             Map<String ,Object> map) {

        PageRequest pageRequest = new PageRequest(page -1 , size);
        Page<OrderDTO> orderDTOS = orderService.findList(pageRequest);
        map.put("orderDTOS" , orderDTOS);
        map.put("currentPage" , page);
        map.put("size" , size);
        return new ModelAndView("order/list" , map);
    }

    @GetMapping("cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId ,
                               Map<String,Object> map) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (GameException e) {
            log.error("【卖家取消订单】发生异常，e={}" , e);
            map.put("msg" , e.getMessage());
            map.put("url" , "list");
            return new ModelAndView("/common/error" , map);

        }


        map.put("msg" , ResultEnum.CANCEL_ORDER_SUCCESS.getMessage());
        map.put("url" , "list");
        return new ModelAndView("/common/success" , map);

    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,
                               Map<String,Object> map) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (GameException e) {
            log.error("【卖家查询订单详情】发生错误，e={}" , e);
            map.put("msg" , e.getMessage());
            map.put("url" , "list");
            return new ModelAndView("/common/error" , map);
        }

        map.put("orderDTO" , orderDTO);
        return new ModelAndView("order/detail" , map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,
                               Map<String,Object> map) {
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (GameException e) {
            log.error("【卖家完结订单】发生错误，e={}" , e);
            map.put("msg" , e.getMessage());
            map.put("url" , "list");
            return new ModelAndView("/common/error" , map);
        }

        map.put("msg" , ResultEnum.FINISH_ORDER_SUCCESS.getMessage());
        map.put("url" , "list");
        return new ModelAndView("/common/success" , map);
    }

}
