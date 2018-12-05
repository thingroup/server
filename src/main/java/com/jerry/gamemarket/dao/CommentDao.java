package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/11/30 0030 20:32
 */
public interface CommentDao extends JpaRepository<ArticleComment,String> {

    List<ArticleComment> findByArticleId(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET likes=likes+1 WHERE article_comment_id=?1",nativeQuery = true)
    void addLike(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET dislikes=dislikes+1 WHERE article_comment_id=?1",nativeQuery = true)
    void addDisLike(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET likes=likes-1 WHERE article_comment_id=?1",nativeQuery = true)
    void reduceLike(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET dislikes=dislikes-1 WHERE article_comment_id=?1",nativeQuery = true)
    void reduceDisLike(String articleId);


}
