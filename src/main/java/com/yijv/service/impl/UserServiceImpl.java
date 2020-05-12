package com.yijv.service.impl;

import com.yijv.bean.Collect;
import com.yijv.bean.UserAuth;
import com.yijv.bean.UserInfo;
import com.yijv.dao.IUserDao;
import com.yijv.pojo.CollectView;
import com.yijv.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserInfo findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    @Override
    public void addUser(String phone, String password) {
        userDao.addUser(phone, password);
    }

    @Override
    public void saveEdit(UserInfo userInfo) {
        userDao.saveEdit(userInfo);
    }

    @Override
    public UserAuth findUserAuthById(Integer userId) {
        return userDao.findUserAuthById(userId);
    }

    @Override
    public void saveAuth(UserAuth userAuth) {
        userDao.saveAuth(userAuth);
    }

    @Override
    public void deleteAuthById(Integer userId) {
        userDao.deleteAuthById(userId);
    }

    @Override
    public void updPassword(UserInfo user) {
        userDao.updPassword(user);
    }

    @Override
    public List<CollectView> getCollectViewById(Integer userId) {
        return userDao.getCollectViewById(userId);
    }

    @Override
    public Collect findCollectById(Integer userId, Integer houseId) {
        return userDao.findCollectById(userId,houseId);
    }

    @Override
    public void addCollect(Collect collect) {
        userDao.addCollect(collect);
    }

    @Override
    public void deleteCollect(Integer collId) {
        userDao.deleteCollect(collId);
    }
}
