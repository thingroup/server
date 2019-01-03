package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.BanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 叶俊晖
 * @date 2019/1/2 0002 16:28
 */
@Repository
public interface BanUserDao extends JpaRepository<BanUser,String>{

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ban_user WHERE user_id=?1",nativeQuery = true)
    void deleteByUserId(String uid);
}
