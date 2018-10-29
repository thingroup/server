package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfos =productDao.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfos.size());
    }
    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("148454");
        productInfo.setProductName("龙珠超宇宙2");
        productInfo.setProductPrice(new BigDecimal(200.00));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("高度还原剧情，粉丝力荐");
        productInfo.setCategoryType(3);
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("http://");
        ProductInfo result = productDao.save(productInfo);
        Assert.assertNotNull(result);
    }
}