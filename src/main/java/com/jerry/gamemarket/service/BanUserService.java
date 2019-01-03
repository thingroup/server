package com.jerry.gamemarket.service;

/**
 * @author 叶俊晖
 * @date 2019/1/2 0002 16:23
 */
public interface BanUserService {
    void BanUser(String uid,String uname,String reason);
    void allowUser(String uid);

    void updateARole(Integer articleId);
    void updateCRole(Integer commentId);

    void ReturnARole(Integer articleId);

    void ReturnCRole(Integer commentId);

    void updateCARole(Integer articleId);

    void updateCCRole(Integer commentId);

    void ReturnCARole(Integer articleId);

    void ReturnCCRole(Integer commentId);
}
