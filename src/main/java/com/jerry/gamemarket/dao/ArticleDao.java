package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.Article;
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
public interface ArticleDao extends JpaRepository<Article,String>{

    @Query(value = "SELECT * FROM article WHERE canteen_id=?1 and role!=-1 AND role!=2",nativeQuery = true)
    List<Article> findByCanteenId(String canteenId);

    @Query(value = "Select * from article",nativeQuery = true)
    List<Article> findAll();

    Article findByUserIdAndCanteenId(String uid,String canteenId);

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

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET role=-1 WHERE article_id=?1",nativeQuery = true)
    void updateRole(Integer articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET role=0 WHERE article_id=?1",nativeQuery = true)
    void ReturnRole(Integer articleId);
}
