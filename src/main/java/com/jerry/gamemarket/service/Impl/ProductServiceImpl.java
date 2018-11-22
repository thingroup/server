package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dao.ProductDao;
import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.enums.ProductStatusEnum;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public ProductInfo findOne(String productId) {

        return productDao.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {

        return productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
//分页查询
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productDao.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productDao.getOne(cartDTO.getProductId());
            if (productInfo == null) {
                log.error("【商品】无商品详情，productInfo == {}",productInfo );
                throw new GameException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productDao.save(productInfo);
        }
    }
    @Transactional
    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
     for(CartDTO cartDTO : cartDTOList){
         ProductInfo productInfo = productDao.getOne(cartDTO.getProductId());
         if(productInfo==null){
             log.error("【商品】无商品详情，productInfo == {}",productInfo );
             throw  new GameException(ResultEnum.PRODUCT_NOT_EXIST);
         }
         Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
         if(result<0){
             log.error("【商品】商品库存不足，productInfo == {}",productInfo );
             throw  new GameException(ResultEnum.PRODUCT_NOT_ENOUGH);
         }
         productInfo.setProductStock(result);
         productDao.save(productInfo);
     }
    }
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productDao.getOne(productId);
        if (productInfo == null) {
            throw new GameException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus() == ProductStatusEnum.UP.getCode()) {
            throw new GameException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productDao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productDao.getOne(productId);
        if (productInfo == null) {
            throw new GameException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new GameException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productDao.save(productInfo);
    }
}