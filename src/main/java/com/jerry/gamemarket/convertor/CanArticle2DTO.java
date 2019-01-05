package com.jerry.gamemarket.convertor;

import com.jerry.gamemarket.dto.CanArticleDTO;
import com.jerry.gamemarket.entity.CanteenArticle;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2019/1/4 0004 13:26
 */
public class CanArticle2DTO {

    public CanArticleDTO convert(CanteenArticle article){
        if(article==null){
            return null;
        }
        CanArticleDTO canArticleDTO=new CanArticleDTO();
        BeanUtils.copyProperties(article,canArticleDTO);
        return canArticleDTO;
    }

    public List<CanArticleDTO> convert(List<CanteenArticle> articles){
        return articles.stream().map(e->(convert(e))).collect(Collectors.toList());
    }
}
