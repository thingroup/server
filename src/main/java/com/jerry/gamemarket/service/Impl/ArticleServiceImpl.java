package com.jerry.gamemarket.service.Impl;


import com.jerry.gamemarket.convertor.Comment2DTO;
import com.jerry.gamemarket.dao.ArticleDao;
import com.jerry.gamemarket.dao.CommentDao;
import com.jerry.gamemarket.dao.LikeHistoryDao;
import com.jerry.gamemarket.dao.OrderDetailDao;
import com.jerry.gamemarket.dto.*;
import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.ArticleComment;
import com.jerry.gamemarket.entity.LikeHistory;
import com.jerry.gamemarket.service.ArticleService;
import com.jerry.gamemarket.utils.ArticleSort.ArticleSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 11:02
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    LikeHistoryDao likeHistoryDao;

    @Override
    public List<ArticleDTO> ArticleList(String canteenId) {
        List<Article> articles=articleDao.findByCanteenId(canteenId);
        List<ArticleDTO> articleDTOS=new ArrayList<>();
        for (Article a:
             articles) {
            ArticleDTO dto=new ArticleDTO();
            BeanUtils.copyProperties(a,dto);
            List<String> names=orderDetailDao.findByOrderId(a.getOrderId()).
                    stream().map(e->e.getProductName()).collect(Collectors.toList());
            dto.setProductNames(names);
            List<CommentDTO> commentDTOS=new Comment2DTO().convert(commentDao.findByArticleId(a.getArticleId()));
            dto.setCommentList(commentDTOS);
            articleDTOS.add(dto);
        }
        return new ArticleSort().Sort(articleDTOS);
    }

    @Override
    public void deleteArticle(Integer articleId) {
        articleDao.deleteById(articleId+"");
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentDao.deleteById(commentId+"");
    }

    @Override
    public void createArticle(CreateArticle createArticle) {
        Article article=new Article();
        BeanUtils.copyProperties(createArticle,article);
        article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        article.setLikes(0);
        article.setDislikes(0);
        articleDao.save(article);
    }

    @Override
    public void createComment(CreateComment comment) {
        ArticleComment articleComment=new ArticleComment();
        BeanUtils.copyProperties(comment,articleComment);
        articleComment.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        articleComment.setLikes(0);
        articleComment.setDislikes(0);
        commentDao.save(articleComment);
    }

    @Override
    public void addAlike(LikeDTO likeDTO) {
        createLikes(likeDTO);
        articleDao.addLike(likeDTO.getArticleId());
    }

    @Override
    public void reduceAlike(LikeDTO likeDTO) {
        likeHistoryDao.deleteByA(likeDTO.getArticleId(),likeDTO.getUserId());
        articleDao.reduceLike(likeDTO.getArticleId());
    }

    @Override
    public void addClike(LikeDTO likeDTO) {
        createLikes(likeDTO);
        commentDao.addLike(likeDTO.getArticleCommentId());
    }

    @Override
    public void reduceClike(LikeDTO likeDTO) {
        likeHistoryDao.deleteByC(likeDTO.getArticleCommentId(),likeDTO.getUserId());
        commentDao.reduceLike(likeDTO.getArticleCommentId());
    }

    @Override
    public void addAHate(LikeDTO likeDTO) {
        createLikes(likeDTO);
        articleDao.addDisLike(likeDTO.getArticleId());
    }

    @Override
    public void reduceAHate(LikeDTO likeDTO) {
        likeHistoryDao.deleteByA(likeDTO.getArticleId(),likeDTO.getUserId());
        articleDao.reducedisLike(likeDTO.getArticleId());
    }

    @Override
    public void addCHate(LikeDTO likeDTO) {
        createLikes(likeDTO);
        commentDao.addDisLike(likeDTO.getArticleCommentId());
    }

    @Override
    public void reduceCHate(LikeDTO likeDTO) {
        likeHistoryDao.deleteByC(likeDTO.getArticleCommentId(),likeDTO.getUserId());
        commentDao.reduceDisLike(likeDTO.getArticleCommentId());
    }

    void createLikes(LikeDTO likeDTO){
        LikeHistory likeHistory=new LikeHistory();
        BeanUtils.copyProperties(likeDTO,likeHistory);
        likeHistory.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        likeHistoryDao.save(likeHistory);
    }
}
