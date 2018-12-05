package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.ArticleDTO;
import com.jerry.gamemarket.entity.Article;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 10:05
 */
public class Article2DTO {
    public static ArticleDTO convert(Article article){
        ArticleDTO dto=new ArticleDTO();
        BeanUtils.copyProperties(article,dto);
        return dto;
    }

    public List<ArticleDTO> convert(List<Article> articleList){
        return articleList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
