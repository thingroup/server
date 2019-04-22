package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 叶俊晖
 * @date 2019/3/20 0020 15:20
 */
@Repository
public interface NormalUserDao extends JpaRepository<NormalUser,String>{
    NormalUser findByUserName(String username);

    NormalUser findByUserPhone(String phone);

    NormalUser findByUserEmail(String email);

    @Modifying
    @Transactional
    @Query(value ="update normal_user SET user_role=-user_role where user_name=?1",nativeQuery = true)
    void updateRole(String user_name);

    @Modifying
    @Transactional
    @Query(value ="update normal_user SET user_role=-user_role where user_id=?1",nativeQuery = true)
    void updateRoleById(String uid);
}
