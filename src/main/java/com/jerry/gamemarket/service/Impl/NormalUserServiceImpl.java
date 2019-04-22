package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dao.NormalUserDao;
import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.NormalUser;
import com.jerry.gamemarket.form.CreateUser;
import com.jerry.gamemarket.service.NormalUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 叶俊晖
 * @date 2019/3/20 0020 16:07
 */
@Service
public class NormalUserServiceImpl implements NormalUserService{

    @Autowired
    NormalUserDao userDao;

    @Override
    public NormalUser checkLogin(String uname, String upass) {
        //用户不存在
        int check=isExist(uname);
        if(check<0){
            return null;
        }
        NormalUser ban=new NormalUser();
        NormalUser user=new NormalUser();
        if(check==1){
            user=userDao.findByUserName(uname);
        }
        if(check==2){
            user=userDao.findByUserPhone(uname);
        }
        if(check==3){
            user=userDao.findByUserEmail(uname);
        }
        if(user.getUserRole()<0){
            //被禁用用户
            ban.setUserRole(-1);
            return ban;
        }
        if(user.getUserPass().equals(upass)){
            return user;
        }
        ban.setUserRole(-2);
        return ban;//密码不正确
    }

    @Override
    public int isExist(String uname) {
        if(userDao.findByUserName(uname)!=null){
            return 1;
        }else if(userDao.findByUserPhone(uname)!=null){
            return 2;
        }else if(userDao.findByUserEmail(uname)!=null){
            return 3;
        }
        return -1;
    }

    @Override
    public void updateRole(String uname) {
        userDao.updateRole(uname);
    }

    @Override
    public NormalUser register(CreateUser createUser) {
        NormalUser user=new NormalUser();
        BeanUtils.copyProperties(createUser,user);
        user.setUserRole(1);
        userDao.save(user);
        return checkLogin(createUser.getUserName(),createUser.getUserPass());
    }

    @Override
    public void updateRoleByUid(String uid) {
        userDao.updateRoleById(uid);
    }
}
