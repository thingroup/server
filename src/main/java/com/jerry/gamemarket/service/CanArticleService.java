package com.jerry.gamemarket.service;



import com.jerry.gamemarket.entity.CanteenArticle;
import com.jerry.gamemarket.entity.CanteenComment;
import com.jerry.gamemarket.form.CreateComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
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

}
