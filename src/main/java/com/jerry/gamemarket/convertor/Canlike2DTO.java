package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.CanLikeDTO;
import com.jerry.gamemarket.entity.CanlikeHistory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 14:09
 */
public class Canlike2DTO {

    public CanLikeDTO convert(CanlikeHistory canlikeHistory){
        if(canlikeHistory==null){
            return null;
        }
        CanLikeDTO canLikeDTO=new CanLikeDTO();
        BeanUtils.copyProperties(canlikeHistory,canLikeDTO);
        return canLikeDTO;
    }

    public List<CanLikeDTO> convert(List<CanlikeHistory> histories){
        return histories.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
