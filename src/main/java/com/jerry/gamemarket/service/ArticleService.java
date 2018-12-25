package com.jerry.gamemarket.service;

import com.jerry.gamemarket.dto.*;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 10:48
 */
public interface ArticleService {
    List<ArticleDTO> ArticleList(String canteenId);

    void deleteArticle(Integer articleId);

    void deleteComment(Integer commentId);

    void createArticle(CreateArticle article);
    void createComment(CreateComment comment);

    //点赞/踩
    void addAlike(LikeDTO likeDTO);
    void reduceAlike(LikeDTO likeDTO);
    void addClike(LikeDTO likeDTO);
    void reduceClike(LikeDTO likeDTO);

    void addAHate(LikeDTO likeDTO);
    void reduceAHate(LikeDTO likeDTO);
    void addCHate(LikeDTO likeDTO);
    void reduceCHate(LikeDTO likeDTO);
}
