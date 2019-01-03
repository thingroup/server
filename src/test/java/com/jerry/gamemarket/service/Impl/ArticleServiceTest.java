package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.VO.ArticleVO;
import com.jerry.gamemarket.dto.ArticleDTO;
import com.jerry.gamemarket.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void TypeTest(){
        List<ArticleDTO> dtos=service.ArticleList("1");
        List<ArticleVO> articleVOS=new ArrayList<>();
        //测试用例uid=abc
        String uid="abc";
        for (ArticleDTO x:
                dtos  ) {
            ArticleVO avo=new ArticleVO();
            BeanUtils.copyProperties(x,avo);
            articleVOS.add(avo);
        }
        articleVOS.forEach(System.out::println);
    }

    @Test
    public void likeTest(){
        System.out.println();
    }
}
