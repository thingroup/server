package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Random;

/**
 * @author 叶俊晖
 * @date 2019/1/2 0002 22:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleDAOTest {
    @Autowired
    ArticleDao articleDao;

    @Test
    public void createData(){
        String s="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s1="fdaefsefgsdgdfhgerargsdfgvsghrweifhsdovnwfwofsaqnfFSDHFOSGNSDfjsdofNIOPJFSO";
        String[] x=new String[5];
        x[0]="好吃点评";
        x[1]="还不错";
        x[2]="Nice";
        x[3]="可以啊";
        x[4]="差评";
        for(int i=2;i<=100;i++){
            Article article=new Article();
            article.setScore(new Random().nextFloat()*5);
            article.setUserId("testUser_"+i);
            article.setUserName("testUserName_"+i);
            article.setCanteenId((new Random().nextInt(4)+1)+"");
            article.setOrderId("testOder_"+i);
            int a=new Random().nextInt(s1.length());
            int b=new Random().nextInt(s1.length());
            int min=0;
            int max=s1.length();
            if(a>b){
                max=a;
                min=b;
            }else{
                max=b;
                max=a;
            }
            article.setDislikes(0);
            article.setLikes(0);
            article.setRole(0);
            article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            article.setArticleText(s1.substring(min,max));
            article.setArticleName(x[new Random().nextInt(5)]);
            articleDao.save(article);
        }
    }
}
