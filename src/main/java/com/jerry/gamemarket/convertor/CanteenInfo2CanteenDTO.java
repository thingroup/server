package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.CanteenDTO;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.CanteenInfo;
import com.jerry.gamemarket.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author by 李兆杰
 * Date 2018/11/22
 */
public class CanteenInfo2CanteenDTO {
    public  static CanteenDTO convert(CanteenInfo canteenInfo){
        CanteenDTO canteenDTO = new CanteenDTO();
        BeanUtils.copyProperties(canteenInfo,canteenDTO);
        return canteenDTO;
    }
    public  static List<CanteenDTO> convert(List<CanteenInfo> canteenInfoList){
        return  canteenInfoList.stream().map(e-> convert(e)).collect(Collectors.toList());
    }
}
