package com.jerry.gamemarket.convertor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.dto.SearchOrderDTO;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.form.OrderForm;
import com.jerry.gamemarket.form.SearchForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * author by 李兆杰
 * Date 2018/10/8
 */
@Slf4j
public class Searchform2SearchOrderDTOConvertor {
  public static SearchOrderDTO convert(SearchForm searchForm){
      SearchOrderDTO searchOrderDTO =new SearchOrderDTO();
      searchOrderDTO.setOrderId(searchForm.getOrderId());
      searchOrderDTO.setBuyerName(searchForm.getBuyerName());
      searchOrderDTO.setBuyerPhone(searchForm.getBuyerPhone());
      searchOrderDTO.setBuyerAddress(searchForm.getBuyerAddress());
      searchOrderDTO.setCanteenName(searchForm.getCanteenName());
      searchOrderDTO.setMaxAmount(searchForm.getMaxAmount());
      searchOrderDTO.setMinAmount(searchForm.getMinAmount());
      searchOrderDTO.setOrderStatus(searchForm.getOrderStatus());
      searchOrderDTO.setPayStatus(searchForm.getPayStatus());

      return searchOrderDTO;
  }
}
