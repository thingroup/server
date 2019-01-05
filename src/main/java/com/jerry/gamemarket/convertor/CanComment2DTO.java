package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.CanCommentDTO;
import com.jerry.gamemarket.entity.CanteenComment;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 14:54
 */
public class CanComment2DTO extends CanCommentDTO {

    public CanCommentDTO convert(CanteenComment comment){
        if(comment==null){
            return null;
        }
        CanCommentDTO canCommentDTO=new CanComment2DTO();
        BeanUtils.copyProperties(comment,canCommentDTO);
        return canCommentDTO;
    }

    public List<CanCommentDTO> convert(List<CanteenComment> list){
        return list.stream().map(e->convert(e)).collect(Collectors.toList());
    }

}
