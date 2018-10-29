package com.jerry.gamemarket.dao;
import com.jerry.gamemarket.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
/*
* Dao层单元测试
* author by 李兆杰
* 2018-10-4
* */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void  findOneTest(){
        ProductCategory productCategory = productCategoryDao.getOne(1);
        System.out.println(productCategory.toString());
    }
    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("公主最爱",3);
        ProductCategory result=productCategoryDao.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null,result);
    }
    @Test
//    出现异常就回滚
    @Transactional
    public void UpdateTest(){
        ProductCategory productCategory = productCategoryDao.getOne(1);
        productCategory.setCategoryName("王子最爱");
        productCategory.setCategoryType(10);
        productCategoryDao.save(productCategory);
    }
    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list= Arrays.asList(2,3,4);
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());

    }

}
