package com.jerry.gamemarket.dao;
import com.jerry.gamemarket.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
* 游戏产品DAO，数据资源库
* author by 李兆杰
* 2018-10-4
* */
import java.util.List;
@Repository
/*
* 产品信息DAO，数据资源库
* author by 李兆杰
* 2018-10-4
* */
public interface ProductDao extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus (Integer productStatus);
    List<ProductInfo> findByCanteenId (String canteenId);

}
