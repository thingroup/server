package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.BuyerService;
import com.jerry.gamemarket.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author by 李兆杰
 * Date 2018/10/8
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openId,orderId);
        if(orderDTO == null){
            log.error("【取消订单】查不到该订单，orderid={}",orderId);
            throw new GameException(ResultEnum.Order_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    @Override
    public OrderDTO findOrderlist(String openId, String orderId) {
        return null;
    }

    @Override
    public OrderDTO cancelOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId,orderId);
    }
    private OrderDTO checkOrderOwner(String openId,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("【查询订单】订单的openid不一致。openid={}",openId,orderDTO);
            throw  new GameException(ResultEnum.OPENID_ERROR);
        }
        return orderDTO;
    }
}
