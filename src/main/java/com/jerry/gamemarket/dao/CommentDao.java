package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/11/30 0030 20:32
 */
@Repository
public interface CommentDao extends JpaRepository<ArticleComment,String> {

    @Query(value = "SELECT * FROM article_comment WHERE article_id=?1 and role!=-1 ORDER BY update_time DESC ",nativeQuery = true)
    List<ArticleComment> findByArticleId(Integer articleId);

    @Query(value = "select * from article_comment WHERE user_id=?1 ORDER by update_time desc limit 1;",nativeQuery = true)
    ArticleComment latestOne(String uid);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET likes=likes+1 WHERE article_comment_id=?1",nativeQuery = true)
    void addLike(String commentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET dislikes=dislikes+1 WHERE article_comment_id=?1",nativeQuery = true)
    void addDisLike(String commentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET likes=likes-1 WHERE article_comment_id=?1",nativeQuery = true)
    void reduceLike(String commentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET dislikes=dislikes-1 WHERE article_comment_id=?1",nativeQuery = true)
    void reduceDisLike(String commentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM article_comment WHERE article_comment_id=?1",nativeQuery = true)
    void deleteById(String commentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM article_comment WHERE article_id=?1",nativeQuery = true)
    void deleteByAId(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET role=-1 WHERE article_comment_id=?1",nativeQuery = true)
    void updateRole(Integer commentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article_comment SET role=0 WHERE article_comment_id=?1",nativeQuery = true)
    void ReturnRole(Integer commentId);

    @Query(value = "SELECT * FROM article_comment WHERE article_id=?1 ORDER BY update_time DESC ",nativeQuery = true)
    List<ArticleComment> findByAidManager(Integer articleId);
}
