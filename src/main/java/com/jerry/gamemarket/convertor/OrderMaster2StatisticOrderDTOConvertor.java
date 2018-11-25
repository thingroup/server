package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.dto.StatisticOrderDTO;
import com.jerry.gamemarket.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
public class OrderMaster2StatisticOrderDTOConvertor {
    public  static StatisticOrderDTO convert(OrderMaster orderMaster){
        StatisticOrderDTO statisticOrderDTO = new StatisticOrderDTO();
        BeanUtils.copyProperties(orderMaster,statisticOrderDTO);
        return statisticOrderDTO;
    }
    public  static List<StatisticOrderDTO> convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e-> convert(e)).collect(Collectors.toList());
    }
}
