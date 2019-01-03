package com.jerry.gamemarket.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 叶俊晖
 * @date 2018/12/26 0026 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Service
public class LikeDAO {

    @Autowired
    LikeHistoryDao likeHistoryDao;

    @Test
    public void findOne(){
        System.out.println(likeHistoryDao.findByUserIdAndArticleId("abc","2"));
    }

}
