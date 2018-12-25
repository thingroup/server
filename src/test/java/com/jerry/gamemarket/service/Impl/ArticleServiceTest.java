package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dto.CreateArticle;
import com.jerry.gamemarket.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 叶俊晖
 * @date 2018/12/5 0005 13:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
    @Autowired
    ArticleService service;

    @Test
    public void SortTest(){
        service.ArticleList("1").forEach(System.out::println);
    }

}
