package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
public class OrderMaster2OrderDTOConvertor {
    public  static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
    public  static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e-> convert(e)).collect(Collectors.toList());
    }
}
