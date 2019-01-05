package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.CanlikeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 15:11
 */
public interface CanLikeHistoryDao extends JpaRepository<CanlikeHistory,String> {
    List<CanlikeHistory> findByUserId(String userId);

    @Query(value = "SELECT * FROM canlike_history WHERE user_id=?1 AND ca_id=?2 AND can_comment_id IS NULL ",nativeQuery = true)
    CanlikeHistory findByUserIdAndArticleId(String userId, String articleId);

    @Query(value = "SELECT * FROM canlike_history WHERE user_id=?1 AND can_comment_id=?2",nativeQuery = true)
    CanlikeHistory findByUserIdAndCommentId(String userId, String commentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canlike_history SET type=-type WHERE ca_id=?1 AND user_id=?2 AND  can_comment_id is NULL ",nativeQuery = true)
    void changeTypeByA(String articleId, String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM canlike_history WHERE ca_id=?1 AND user_id=?2 AND can_comment_id is NULL ",nativeQuery = true)
    void deleteByA(String articleId, String userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canlike_history SET type=-type WHERE can_comment_id=?1 AND user_id=?2 ",nativeQuery = true)
    void changeTypeByC(String commentId, String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM canlike_history WHERE can_comment_id=?1 AND user_id=?2",nativeQuery = true)
    void deleteByC(String commentId, String userId);

}
