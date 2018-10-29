package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.dao.CanteenDao;
import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.entity.CanteenInfo;
import com.jerry.gamemarket.enums.CanteenStatusEnum;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author by 李兆杰
 * Date 2018/10/7
 */
@Service
public class CanteenServiceImpl  implements CanteenService{
    @Autowired
    private CanteenDao canteenDao;
    @Override
    public CanteenInfo findOne(String canteenId) {
        return canteenDao.getOne(canteenId);
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
