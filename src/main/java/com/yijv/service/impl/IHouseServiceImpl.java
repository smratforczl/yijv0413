package com.yijv.service.impl;

import com.github.pagehelper.PageHelper;
import com.yijv.dao.IHouseDao;
import com.yijv.pojo.HouseView;
import com.yijv.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IHouseServiceImpl implements IHouseService {

    @Autowired
    private IHouseDao houseDao;

    @Override
    public List<HouseView> findFourHouse(Integer houseType) {
        return houseDao.findFourHouse(houseType);
    }

    @Override
    public List<HouseView> findAllHouseByHouseType(Integer page,Integer houseType) {
        PageHelper.startPage(page,5);
        return houseDao.findAllHouseByHouseType(houseType);
    }

    @Override
    public HouseView findHouseById(Integer houseId) {
        return houseDao.findHouseById(houseId);
    }
}
