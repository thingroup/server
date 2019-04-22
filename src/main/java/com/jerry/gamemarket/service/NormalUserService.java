package com.jerry.gamemarket.service;

import com.jerry.gamemarket.entity.NormalUser;
import com.jerry.gamemarket.form.CreateUser;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/3/20 0020 16:07
 */
public interface NormalUserService {
    //验证登录
    NormalUser checkLogin(String uname,String upass);
    //用户注册查重
    int isExist(String uname);
    //用户权限更新
    void updateRole(String uname);
    //注册用户
    NormalUser register(CreateUser createUser);

    void updateRoleByUid(String uid);
}
