package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.LikeDTO;
import com.jerry.gamemarket.entity.LikeHistory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2018/12/25 0025 15:44
 */
public class Like2DTO {

    public LikeDTO convert(LikeHistory history){
        LikeDTO likeDTO=new LikeDTO();
        BeanUtils.copyProperties(history,likeDTO);
        return likeDTO;
    }

    public List<LikeDTO>convert(List<LikeHistory> list){
        return list.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
