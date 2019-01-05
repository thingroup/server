package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.convertor.CanArticle2DTO;
import com.jerry.gamemarket.convertor.Canlike2DTO;
import com.jerry.gamemarket.dao.CanCommentDao;
import com.jerry.gamemarket.dao.CanLikeHistoryDao;
import com.jerry.gamemarket.dao.CanteenArticleDao;
import com.jerry.gamemarket.dto.CanArticleDTO;
import com.jerry.gamemarket.convertor.CanComment2DTO;
import com.jerry.gamemarket.dto.CanCommentDTO;
import com.jerry.gamemarket.dto.CanLikeDTO;
import com.jerry.gamemarket.entity.*;
import com.jerry.gamemarket.form.CreateArticle;
import com.jerry.gamemarket.form.CreateComment;
import com.jerry.gamemarket.service.CanArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/1/3 0003 14:43
 */
@Service
public class CanArticleServiceImpl implements CanArticleService {

    @Autowired
    private CanteenArticleDao articleDao;

    @Autowired
    CanCommentDao commentDao;

    @Autowired
    CanLikeHistoryDao likeHistoryDao;

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

    @Override
    public CanArticleDTO OneArticle(String userId, String canteenId) {
        return new CanArticle2DTO().convert(articleDao.findByUidCid(userId,canteenId));
    }

    @Override
    public void createArticle(CreateArticle article) {
        CanteenArticle canteenArticle=new CanteenArticle();
        BeanUtils.copyProperties(article,canteenArticle);
        canteenArticle.setText(article.getArticleText());
        canteenArticle.setLikes(0);
        canteenArticle.setDislikes(0);
        canteenArticle.setRole(0);
        canteenArticle.setName(article.getArticleName());
        canteenArticle.setImg(article.getArticleImg());
        articleDao.save(canteenArticle);
    }

    @Override
    public List<CanArticleDTO> ArticleList() {
        return new CanArticle2DTO().convert(articleDao.getAll());
    }

    @Override
    public List<CanLikeDTO> LikeList(String uid) {
        return new Canlike2DTO().convert(likeHistoryDao.findByUserId(uid));
    }

    @Override
    public CanLikeDTO findByUidAid(String uid, Integer caId) {
        return new Canlike2DTO().convert(likeHistoryDao.findByUserIdAndArticleId(uid,caId+""));
    }

    @Override
    public CanCommentDTO LatestComment(String userId) {
        return new CanComment2DTO().convert(commentDao.latestOne(userId));
    }

    @Override
    public void deleteArticle(Integer articleId, String uid) {
        articleDao.deleteByAidUid(articleId,uid);
    }

    @Override
    public void deleteCommentByUid(Integer commentId, String uid) {
        commentDao.deleteByCidUid(commentId,uid);
    }

    @Override
    public void addAlike(CanLikeDTO likeDTO) {
        createLikes(likeDTO);
        articleDao.addLike(likeDTO.getCaId());
    }

    @Override
    public void addClike(CanLikeDTO likeDTO) {
        createLikes(likeDTO);
        commentDao.addLike(likeDTO.getCanCommentId());
    }

    @Override
    public void addAHate(CanLikeDTO likeDTO) {
        createLikes(likeDTO);
        articleDao.addHate(likeDTO.getCaId());
    }

    @Override
    public void addCHate(CanLikeDTO likeDTO) {
        createLikes(likeDTO);
        commentDao.addHate(likeDTO.getCanCommentId());
    }

    @Override
    public void reduceAlike(CanLikeDTO likeDTO) {
        likeHistoryDao.deleteByA(likeDTO.getCaId(),likeDTO.getUserId());
        articleDao.reduceLike(likeDTO.getCaId());
    }

    @Override
    public void reduceClike(CanLikeDTO likeDTO) {
        likeHistoryDao.deleteByC(likeDTO.getCanCommentId(),likeDTO.getUserId());
        commentDao.reduceLike(likeDTO.getCanCommentId());
    }

    @Override
    public void reduceAHate(CanLikeDTO likeDTO) {
        likeHistoryDao.deleteByA(likeDTO.getCaId(),likeDTO.getUserId());
        articleDao.reduceHate(likeDTO.getCaId());
    }

    @Override
    public void reduceCHate(CanLikeDTO likeDTO) {
        likeHistoryDao.deleteByC(likeDTO.getCanCommentId(),likeDTO.getUserId());
        commentDao.reduceHate(likeDTO.getCanCommentId());
    }

    @Override
    public void changeLike(CanLikeDTO likeDTO) {
        if(likeDTO.getCanCommentId()==null){
            likeHistoryDao.changeTypeByA(likeDTO.getCaId(),likeDTO.getUserId());
            if(likeDTO.getType()>0){
                articleDao.addLike(likeDTO.getCaId());
                articleDao.reduceHate(likeDTO.getCaId());
            }else{
                articleDao.addHate(likeDTO.getCaId());
                articleDao.reduceLike(likeDTO.getCaId());
            }
        }else{
            likeHistoryDao.changeTypeByC(likeDTO.getCanCommentId(),likeDTO.getUserId());
            if(likeDTO.getType()>0){
                commentDao.addLike(likeDTO.getCanCommentId());
                commentDao.reduceHate(likeDTO.getCanCommentId());
            }else{
                commentDao.addHate(likeDTO.getCanCommentId());
                commentDao.reduceLike(likeDTO.getCanCommentId());
            }
        }
    }

    @Override
    public CanArticleDTO userfindByAid(Integer caid) {
        return new CanArticle2DTO().convert(articleDao.findByCaIdUser(caid));
    }

    @Override
    public List<CanCommentDTO> UserGetCommentsByAidManager(Integer caid) {
        return new CanComment2DTO().convert(commentDao.findByAidUser(caid));
    }

    @Override
    public CanLikeDTO findByUidCid(String userId, Integer canCommentId) {
        return new Canlike2DTO().convert(likeHistoryDao.findByUserIdAndCommentId(userId,canCommentId+""));
    }

    private void createLikes(CanLikeDTO likeDTO) {
        CanlikeHistory history=new CanlikeHistory();
        BeanUtils.copyProperties(likeDTO,history);
        history.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        likeHistoryDao.save(history);
    }
}
