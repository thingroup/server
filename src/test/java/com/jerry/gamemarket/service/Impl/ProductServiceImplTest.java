package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo =productService.findOne("1551215");
        Assert.assertEquals("1551215",productInfo.getProductId());

    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos=productService.findUpAll();
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request=new PageRequest(0,2);
        Page<ProductInfo> productInfoPage=productService.findAll(request);
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());

    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1354856");
        productInfo.setProductName("龙珠斗士Z");
        productInfo.setProductPrice(new BigDecimal(200.00));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("高度还原剧情，粉丝力荐");
        productInfo.setCategoryType(3);
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setProductIcon("http://");
        ProductInfo result=productService.save(productInfo);
        Assert.assertNotNull(result);
    }

}