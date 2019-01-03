package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dao.CanCommentDao;
import com.jerry.gamemarket.dao.CanteenArticleDao;
import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.ArticleComment;
import com.jerry.gamemarket.entity.CanteenArticle;
import com.jerry.gamemarket.entity.CanteenComment;
import com.jerry.gamemarket.form.CreateComment;
import com.jerry.gamemarket.service.CanArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/1/3 0003 14:43
 */
@Service
@Transactional
public class CanArticleServiceImpl implements CanArticleService {

    @Autowired
    private CanteenArticleDao articleDao;

    @Autowired
    CanCommentDao commentDao;

    @Override
    public Page<CanteenArticle> findCanArticle(Pageable request) {
        return articleDao.findAll(request);
    }

    @Override
    public void createComment(CreateComment comment) {
        CanteenComment articleComment=new CanteenComment();
        BeanUtils.copyProperties(comment,articleComment);
        articleComment.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        articleComment.setLikes(0);
        articleComment.setDislikes(0);
        articleComment.setCaId(comment.getArticleId());
        commentDao.save(articleComment);
    }

    @Override
    public void deleteComment(Integer replyId) {
        commentDao.deleteById(replyId+"");
    }

    @Override
    public List<CanteenComment> getCommentsByAidManager(Integer articleId) {
        return commentDao.findByAidManager(articleId);
    }

    @Override
    public CanteenArticle findByAid(Integer caid) {
        return articleDao.findByCaId(caid);
    }
}
