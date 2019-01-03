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
        String s1="fdaefsefgsdgdf复合丝辐射读后感佛文化节佛额外IQ分就偶是地方去放弃分担我覅佛Ian佛我阿尔及发票上只分配hgerargsdfgvsghrweifhsdovnwfwofsaqnfFSDHFOSGNSDfjsdofNIOPJFSO";
        String[] x=new String[5];
        x[0]="深夜小馆";
        x[1]="高师傅家常菜";
        x[2]="辣焖骨头饭";
        x[3]="红篷子烧烤";
        x[4]="差评";
        for(int i=103;i<=200;i++){
            Article article=new Article();
            article.setScore(new Random().nextFloat()*5);
            article.setUserId("testUser_"+i);
            article.setUserName("testUserName_"+i);
            Integer cid=new Random().nextInt(4);
            article.setCanteenId((cid+1)+"");
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
            article.setRole(2);
            article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            article.setArticleText(s1.substring(min,max));
            article.setArticleName(x[cid]);
            articleDao.save(article);
        }
    }
}
