package com.yijv.service;

import com.yijv.pojo.HouseView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHouseService {

    //查询房源信息
    List<HouseView> findFourHouse(Integer houseType);


    List<HouseView> findAllHouseByHouseType(Integer page,Integer houseType);

    HouseView findHouseById(Integer houseId);

}
