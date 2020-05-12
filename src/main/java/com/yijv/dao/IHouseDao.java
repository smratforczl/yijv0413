package com.yijv.dao;

import com.yijv.pojo.HouseView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHouseDao {

    //查询房源信息
    List<HouseView> findFourHouse(@Param("houseType") Integer houseType);

    List<HouseView> findAllHouseByHouseType(@Param("houseType") Integer houseType);

    HouseView findHouseById(Integer houseId);
}
