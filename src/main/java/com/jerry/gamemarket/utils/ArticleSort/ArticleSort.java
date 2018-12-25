package com.jerry.gamemarket.utils.ArticleSort;

import com.jerry.gamemarket.dto.ArticleDTO;
import com.jerry.gamemarket.dto.CommentDTO;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 11:24
 */
public class ArticleSort {
    public List<ArticleDTO> Sort(List<ArticleDTO> list){
        list.sort((o1, o2) ->
                countHot(o2)-countHot(o1)
        );
        return list;
    }

    int countHot(ArticleDTO o){
        return o.getLikes()
                +o.getDislikes()
                +o.getCommentList().size()
                +o.getCommentList()
                .stream().map(CommentDTO::getLikes).
                        reduce(Integer::sum).orElse(0)
                +o.getCommentList()
                .stream().map(CommentDTO::getDislikes).
                        reduce(Integer::sum).orElse(0);
    }
}
