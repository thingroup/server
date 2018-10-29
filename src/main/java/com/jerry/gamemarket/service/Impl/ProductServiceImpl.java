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

import java.util.List;
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
             throw  new GameException(ResultEnum.PRODUCT_NOT_EXIST);
         }
         Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
         if(result<0){
             throw  new GameException(ResultEnum.PRODUCT_NOT_ENOUGH);
         }
         productInfo.setProductStock(result);
         productDao.save(productInfo);
     }
    }
}