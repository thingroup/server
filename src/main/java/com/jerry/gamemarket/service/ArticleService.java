package com.jerry.gamemarket.service;

import com.jerry.gamemarket.dto.*;
import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.ArticleComment;
import com.jerry.gamemarket.form.CreateArticle;
import com.jerry.gamemarket.form.CreateComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 10:48
 */
public interface ArticleService {
    List<ArticleDTO> ArticleList(String canteenId);

    ArticleDTO OneArticle(String uid,String canteenid);

    CommentDTO LatestComment(String uid);

    void deleteArticle(Integer articleId,String uid);

    void deleteComment(Integer commentId);

    void createArticle(CreateArticle article);
    void createComment(CreateComment comment);

    //点赞/踩
    List<LikeDTO> LikeList(String uid,String canteenId);
    LikeDTO findByUidAid(String uid,String aid);
    LikeDTO findByUidCid(String uid,String cid);


    void changeLike(LikeDTO likeDTO);

    void addAlike(LikeDTO likeDTO);
    void reduceAlike(LikeDTO likeDTO);
    void addClike(LikeDTO likeDTO);
    void reduceClike(LikeDTO likeDTO);

    void addAHate(LikeDTO likeDTO);
    void reduceAHate(LikeDTO likeDTO);
    void addCHate(LikeDTO likeDTO);
    void reduceCHate(LikeDTO likeDTO);

    Page<Article> findAll(Pageable request);

    List<ArticleComment> getCommentsByAid(Integer articleId);

    List<ArticleComment> getCommentsByAidManager(Integer articleId);
}
