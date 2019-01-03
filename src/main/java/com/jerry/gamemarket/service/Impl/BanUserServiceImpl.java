package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dao.ArticleDao;
import com.jerry.gamemarket.dao.BanUserDao;
import com.jerry.gamemarket.dao.CommentDao;
import com.jerry.gamemarket.entity.BanUser;
import com.jerry.gamemarket.service.BanUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 叶俊晖
 * @date 2019/1/2 0002 16:23
 */
@Service
@Slf4j
public class BanUserServiceImpl implements BanUserService{
    @Autowired
    BanUserDao banUserDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public void BanUser(String uid, String uname,String reason) {
        BanUser banUser=new BanUser();
        banUser.setReason(reason);
        banUser.setUserId(uid);
        banUser.setUserName(uname);
        banUserDao.save(banUser);
    }

    @Override
    public void allowUser(String uid) {
        banUserDao.deleteByUserId(uid);
    }

    @Override
    public void updateARole(Integer articleId) {
        articleDao.updateRole(articleId);
    }

    @Override
    public void updateCRole(Integer commentId) {
        commentDao.updateRole(commentId);
    }

    @Override
    public void ReturnARole(Integer articleId) {
        articleDao.ReturnRole(articleId);
    }

    @Override
    public void ReturnCRole(Integer commentId) {
        commentDao.ReturnRole(commentId);
    }
}
