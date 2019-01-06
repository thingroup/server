package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.CanteenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
/**
 * 餐厅信息Dao
 * author by 李兆杰
 * 2018-10-07
 * */
public interface CanteenDao extends JpaRepository<CanteenInfo ,String>{
    List<CanteenInfo> findByCanteenStatus (Integer canteenStatus);

    @Query(value = "SELECT star FROM canteen_info WHERE Canteen_id=?1",nativeQuery = true)
    Double getStar(String canteenId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE canteen_info SET star=?1",nativeQuery = true)
    void updateStar(double v);
}
