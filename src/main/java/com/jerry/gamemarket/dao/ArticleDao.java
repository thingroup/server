package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/11/30 0030 20:32
 */
public interface ArticleDao extends JpaRepository<Article,String>{

    List<Article> findByCanteenId(String canteenId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM article WHERE article_id=?1",nativeQuery = true)
    void deleteById(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET likes=likes+1 WHERE article_id=?1",nativeQuery = true)
    void addLike(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET dislikes=dislikes+1 WHERE article_id=?1",nativeQuery = true)
    void addDisLike(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET likes=likes-1 WHERE article_id=?1",nativeQuery = true)
    void reduceLike(String articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET dislikes=dislikes-1 WHERE article_id=?1",nativeQuery = true)
    void reducedisLike(String articleId);
}
