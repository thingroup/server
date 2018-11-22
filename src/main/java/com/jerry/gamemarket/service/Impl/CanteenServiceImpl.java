package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.convertor.CanteenInfo2CanteenDTO;
import com.jerry.gamemarket.convertor.OrderMaster2OrderDTOConvertor;
import com.jerry.gamemarket.dao.CanteenDao;
import com.jerry.gamemarket.dao.ProductDao;
import com.jerry.gamemarket.dto.CanteenDTO;
import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.CanteenInfo;
import com.jerry.gamemarket.entity.OrderMaster;
import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.enums.CanteenStatusEnum;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.CanteenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
@Service
public class CanteenServiceImpl  implements CanteenService{
    @Autowired
    private CanteenDao canteenDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public CanteenDTO findOne(String canteenId) {
        CanteenInfo canteenInfo =canteenDao.getOne(canteenId);
        if (canteenInfo==null){
            throw  new GameException(ResultEnum.Canteen_NOT_EXIST);
        }
        List<ProductInfo> productInfoList = productDao.findByCanteenId(canteenId);
        if (CollectionUtils.isEmpty(productInfoList)){
            throw  new GameException(ResultEnum.CANTEEN_NOT_HAVEGOODS);
        }
        CanteenDTO canteenDTO = new  CanteenDTO();
        BeanUtils.copyProperties(canteenInfo,canteenDTO);
        canteenDTO.setProductInfoList(productInfoList);
        return canteenDTO;
    }

    @Override
    public List<CanteenInfo> findUpAll() {
        return canteenDao.findByCanteenStatus(CanteenStatusEnum.Canteen_OPEN.getCode());
    }

    @Override
    public Page<CanteenInfo> findAll(Pageable pageable) {
        return canteenDao.findAll(pageable);
    }

    @Override
    public CanteenInfo save(CanteenInfo canteenInfo) {
        return canteenDao.save(canteenInfo);
    }

    @Override
    public Page<CanteenDTO> findList(Pageable pageable) {
        Page<CanteenInfo> canteenInfos = canteenDao.findAll(pageable);

        List<CanteenDTO> canteenDTOList = CanteenInfo2CanteenDTO.convert(canteenInfos.getContent());

        return new PageImpl<>(canteenDTOList , pageable , canteenInfos.getTotalElements());

    }
    @Override
    public void increaseStock(String canteenId,Integer roomQuantity) {

            CanteenInfo canteenInfo = canteenDao.getOne(canteenId);
            if (canteenInfo == null) {
                throw new GameException(ResultEnum.Canteen_NOT_EXIST);
            }
            Integer result = canteenInfo.getCanteenRoomStock() + roomQuantity;
            canteenInfo.setCanteenRoomStock(result);
            canteenDao.save(canteenInfo);

    }

    @Override
    public void decreaseStock(String canteenId,Integer roomQuantity) {
            CanteenInfo canteenInfo =canteenDao.getOne(canteenId);
            if(canteenInfo==null){
                throw  new GameException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = canteenInfo.getCanteenRoomStock()-roomQuantity;
            if(result<0){
                throw  new GameException(ResultEnum.Canteen_Full);
            }
            canteenInfo.setCanteenRoomStock(result);
            canteenDao.save(canteenInfo);
        }

}
