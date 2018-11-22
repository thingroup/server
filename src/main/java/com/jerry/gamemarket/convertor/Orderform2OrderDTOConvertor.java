package com.jerry.gamemarket.convertor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * author by 李兆杰
 * Date 2018/10/8
 */
@Slf4j
public class Orderform2OrderDTOConvertor {
  public static OrderDTO convert(OrderForm orderForm){
      Gson gson = new Gson();
      OrderDTO orderDTO =new OrderDTO();
      orderDTO.setBuyerName(orderForm.getName());
      orderDTO.setBuyerPhone(orderForm.getPhone());
      orderDTO.setBuyerAddress(orderForm.getAddress());
      orderDTO.setBuyerOpenid(orderForm.getOpenid());
      List<OrderDetail> orderDetailsList =new ArrayList<>();
      try {
          orderDetailsList =gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
          }.getType());
      }catch (Exception e){
          log.error("【对象转换】错误，string={}",orderForm.getItems());
          throw new GameException(ResultEnum.PARAM_ERROR);
      }
      orderDTO.setOrderDetailList(orderDetailsList);
      return orderDTO;
  }
}
