package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.dto.CanArticleDTO;
import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.CanteenArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Query(value = "SELECT * FROM canteen_article WHERE user_id=?1 AND canteen_id=?2",nativeQuery = true)
    CanteenArticle findByUidCid(String userId, String canteenId);

    @Query(value = "SELECT * FROM canteen_article WHERE role!=-1 ORDER BY update_time DESC ",nativeQuery = true)
    List<CanteenArticle> getAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM canteen_article WHERE ca_id=?1 AND user_id=?2",nativeQuery = true)
    void deleteByAidUid(Integer articleId, String uid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_article SET likes=likes+1 WHERE ca_id=?1",nativeQuery = true)
    void addLike(String caId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_article SET dislikes=dislikes+1 WHERE ca_id=?1",nativeQuery = true)
    void addHate(String caId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_article SET likes=likes-1 WHERE ca_id=?1",nativeQuery = true)
    void reduceLike(String caId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_article SET dislikes=dislikes-1 WHERE ca_id=?1",nativeQuery = true)
    void reduceHate(String caId);

    @Query(value = "SELECT * FROM canteen_article WHERE ca_id=?1 AND role!=-1",nativeQuery = true)
    CanteenArticle findByCaIdUser(Integer caid);

    @Query(value = "SELECT * FROM canteen_article WHERE user_id=?1 AND role!=-1 ORDER BY update_time desc",nativeQuery = true)
    List<CanteenArticle> findMyList(String uid);
}
