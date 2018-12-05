package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.CommentDTO;
import com.jerry.gamemarket.entity.ArticleComment;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 10:17
 */
public class Comment2DTO {
    public static CommentDTO convert(ArticleComment comment){
        CommentDTO commentDTO=new CommentDTO();
        BeanUtils.copyProperties(comment,commentDTO);
        return commentDTO;
    }

    public List<CommentDTO> convert(List<ArticleComment> articleComments){
        return articleComments.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
