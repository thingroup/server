package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.CanteenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
/**
 * 餐厅信息Dao
 * author by 李兆杰
 * 2018-10-07
 * */
public interface CanteenDao extends JpaRepository<CanteenInfo ,String>{
    List<CanteenInfo> findByCanteenStatus (Integer canteenStatus);
}
