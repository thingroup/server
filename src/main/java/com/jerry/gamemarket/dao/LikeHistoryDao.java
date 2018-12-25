package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.LikeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 15:11
 */
public interface LikeHistoryDao extends JpaRepository<LikeHistory,String> {
    List<LikeHistory> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE like_history SET type=-type WHERE article_id=?1 AND user_id=?2 ",nativeQuery = true)
    void changeType(String articleId,String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM like_history WHERE article_id=?1 AND user_id=?2",nativeQuery = true)
    void deleteByA(String articleId,String userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE like_history SET type=-type WHERE article_comment_id=?1 AND user_id=?2 ",nativeQuery = true)
    void changeTypeByA(String commentId,String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM like_history WHERE article_comment_id=?1 AND user_id=?2",nativeQuery = true)
    void deleteByC(String commentId,String userId);
}
