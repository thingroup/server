package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.CanteenArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 叶俊晖
 * @date 2019/1/3 0003 14:34
 */
@Repository
public interface CanteenArticleDao extends JpaRepository<CanteenArticle,String>{

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_article SET role=-1 WHERE ca_id=?1",nativeQuery = true)
    void banRole(Integer articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_article SET role=0 WHERE ca_id=?1",nativeQuery = true)
    void allowRole(Integer articleId);

    @Query(value = "SELECT * FROM canteen_article WHERE ca_id=?1",nativeQuery = true)
    CanteenArticle findByCaId(Integer id);
}
