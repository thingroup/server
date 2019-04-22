package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.Article;
import com.jerry.gamemarket.entity.CanteenArticle;
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
public class CanArticleDAOTest {
    @Autowired
    CanteenArticleDao canteenArticleDao;

    @Test
    public void createData(){
        String s="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s1="阳台望见那月光 儿时睡过那睡床" +
                "如停顿光阴般于故居长驻到天荒" +
                "唯独是你那目光 开始比照片淡黄" +
                "如提示我岁月总爱催人没法反抗" +
                "为何问到你健康 总喜欢去避谈近况" +
                "却又带多点手震斟茶滗汤" +
                "唯求别怪我为生计仅只偶然来往" +
                "把握不了共聚时光" +
                "仍能共你见面尚余下几多次" +
                "多一天更少下次 是我恐怕未能及时" +
                "共晋晚饭共怀念消失那日子" +
                "方知道不爱就恨迟" +
                "时候若不早不可再不在意" +
                "长年独个往外闯 归家先觉未如旧往" +
                "趁尚喝到这碗最家常的汤" +
                "无谓下个节日先记得回到来探访" +
                "一腔挂念对谁讲" +
                "仍能共你见面就来聚多一次" +
                "多一天更少下次 没哪一个从无休止" +
                "若最爱仍在仍愿称呼我孩子" +
                "好想贪多个下一次" +
                "时候若不早多一眼都在意" +
                "明明没有要事赶 仍旧习惯说繁忙" +
                "临行亦叮嘱必须要干掉暖壶的汤" +
                "门牌下老少平安 即使可贴多十年" +
                "仍能共晋晚饭天至知尚有几多趟";
        String[] x=new String[5];
        x[0]="深夜小馆";
        x[1]="高师傅家常菜";
        x[2]="辣焖骨头饭";
        x[3]="红篷子烧烤";
        for(int i=1;i<=5;i++){
            CanteenArticle article=new CanteenArticle();
            article.setScore(new Random().nextFloat()*5);
            article.setUserId("1");
            article.setUserName("龟bb");
            Integer cid=new Random().nextInt(4);
            article.setCanteenId((cid+1)+"");
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
            article.setText(s1.substring(min,max));
            article.setName(x[cid]);
            canteenArticleDao.save(article);
        }
    }
}
