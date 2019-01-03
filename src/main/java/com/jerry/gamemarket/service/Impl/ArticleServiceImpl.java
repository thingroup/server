package com.jerry.gamemarket.service.Impl;


import com.jerry.gamemarket.convertor.Comment2DTO;
import com.jerry.gamemarket.convertor.Like2DTO;
import com.jerry.gamemarket.dao.ArticleDao;
import com.jerry.gamemarket.dao.CommentDao;
import com.jerry.gamemarket.dao.LikeHistoryDao;
import com.jerry.gamemarket.dao.OrderDetailDao;
import com.jerry.gamemarket.dto.*;
import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.ArticleComment;
import com.jerry.gamemarket.entity.LikeHistory;
import com.jerry.gamemarket.form.CreateArticle;
import com.jerry.gamemarket.form.CreateComment;
import com.jerry.gamemarket.service.ArticleService;
import com.jerry.gamemarket.utils.ArticleSort.ArticleSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ArticleDTO OneArticle(String uid, String canteenid) {
        Article article=articleDao.findByUserIdAndCanteenId(uid, canteenid);
        ArticleDTO articleDTO=new ArticleDTO();
        BeanUtils.copyProperties(article,articleDTO);
        return articleDTO;
    }

    @Override
    public CommentDTO LatestComment(String uid) {
        ArticleComment articleComment=commentDao.latestOne(uid);
        CommentDTO commentDTO=new CommentDTO();
        BeanUtils.copyProperties(articleComment,commentDTO);
        return commentDTO;
    }

    @Override
    public void deleteArticle(Integer articleId,String uid) {
        articleDao.deleteById(articleId+"");
        commentDao.deleteByAId(articleId+"");
        likeHistoryDao.deleteByA(articleId+"",uid);
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
    public List<LikeDTO> LikeList(String uid,String canteenId) {
        List<LikeHistory> histories=likeHistoryDao.findByUserIdAndCanteenId(uid,canteenId);
        if(histories==null){
            return null;
        }
        return new Like2DTO().convert(histories);
    }

    @Override
    public LikeDTO findByUidAid(String uid, String aid) {
        LikeHistory likeHistory=likeHistoryDao.findByUserIdAndArticleId(uid,aid);
        if(likeHistory==null){
            return null;
        }
        return new Like2DTO().convert(likeHistory);
    }

    @Override
    public LikeDTO findByUidCid(String uid, String cid) {
        LikeHistory likeHistory=likeHistoryDao.findByUserIdAndArticleCommentId(uid,cid);
        if(likeHistory==null){
            return null;
        }
        return new Like2DTO().convert(likeHistory);
    }

    @Override
    public void changeLike(LikeDTO likeDTO) {
        if(likeDTO.getArticleCommentId()==null){
            likeHistoryDao.changeTypeByA(likeDTO.getArticleId(),likeDTO.getUserId());
            if(likeDTO.getType()>0){
                articleDao.addLike(likeDTO.getArticleId());
                articleDao.reducedisLike(likeDTO.getArticleId());
            }else{
                articleDao.addDisLike(likeDTO.getArticleId());
                articleDao.reduceLike(likeDTO.getArticleId());
            }
        }else{
            likeHistoryDao.changeTypeByC(likeDTO.getArticleCommentId(),likeDTO.getUserId());
            if(likeDTO.getType()>0){
                commentDao.addLike(likeDTO.getArticleCommentId());
                commentDao.reduceDisLike(likeDTO.getArticleCommentId());
            }else{
                commentDao.addDisLike(likeDTO.getArticleCommentId());
                commentDao.reduceLike(likeDTO.getArticleCommentId());
            }
        }
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

    @Override
    public Page<Article> findAll(Pageable request) {
        return articleDao.findAll(request);
    }

    @Override
    public List<ArticleComment> getCommentsByAid(Integer articleId) {
        return commentDao.findByArticleId(articleId);
    }

    @Override
    public List<ArticleComment> getCommentsByAidManager(Integer articleId) {
        return commentDao.findByAidManager(articleId);
    }

    void createLikes(LikeDTO likeDTO){
        LikeHistory likeHistory=new LikeHistory();
        BeanUtils.copyProperties(likeDTO,likeHistory);
        likeHistory.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        likeHistoryDao.save(likeHistory);
    }
}
