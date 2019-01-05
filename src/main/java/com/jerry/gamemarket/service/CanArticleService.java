package com.jerry.gamemarket.service;



import com.jerry.gamemarket.dto.CanArticleDTO;
import com.jerry.gamemarket.dto.CanCommentDTO;
import com.jerry.gamemarket.dto.CanLikeDTO;
import com.jerry.gamemarket.entity.CanteenArticle;
import com.jerry.gamemarket.entity.CanteenComment;
import com.jerry.gamemarket.form.CreateArticle;
import com.jerry.gamemarket.form.CreateComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/1/3 0003 14:38
 */
public interface CanArticleService {

    //后台
    Page<CanteenArticle> findCanArticle(Pageable request);

   void createComment(CreateComment comment);

    void deleteComment(Integer replyId);

    List<CanteenComment> getCommentsByAidManager(Integer articleId);

    CanteenArticle findByAid(Integer caid);

    //前台

    CanArticleDTO OneArticle(String userId, String canteenId);

    void createArticle(CreateArticle article);

    List<CanArticleDTO> ArticleList();

    List<CanLikeDTO> LikeList(String uid);

    CanLikeDTO findByUidAid(String uid, Integer caId);


    CanCommentDTO LatestComment(String userId);

    void deleteArticle(Integer articleId, String uid);

    void deleteCommentByUid(Integer commentId, String uid);

    void addAlike(CanLikeDTO likeDTO);

    void addClike(CanLikeDTO likeDTO);

    void addAHate(CanLikeDTO likeDTO);

    void addCHate(CanLikeDTO likeDTO);

    void reduceAlike(CanLikeDTO likeDTO);

    void reduceClike(CanLikeDTO likeDTO);

    void reduceAHate(CanLikeDTO likeDTO);

    void reduceCHate(CanLikeDTO likeDTO);

    void changeLike(CanLikeDTO likeDTO);

    CanArticleDTO userfindByAid(Integer caid);

    List<CanCommentDTO> UserGetCommentsByAidManager(Integer caid);

    CanLikeDTO findByUidCid(String userId, Integer canCommentId);
}
