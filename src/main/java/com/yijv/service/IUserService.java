package com.yijv.service;

import com.yijv.bean.Collect;
import com.yijv.bean.UserAuth;
import com.yijv.bean.UserInfo;
import com.yijv.pojo.CollectView;
import com.yijv.pojo.HouseView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserService {
    //登录
    UserInfo findUserByPhone(String phone);

    void addUser(String phone, String password);

    void saveEdit(UserInfo userInfo);

    UserAuth findUserAuthById(Integer userId);

    void saveAuth(UserAuth userAuth);

    void deleteAuthById(Integer userId);

    void updPassword(UserInfo user);

    List<CollectView> getCollectViewById(@Param("userId") Integer userId);

    Collect findCollectById(Integer userId, Integer houseId);

    void addCollect(Collect collect);

    void deleteCollect(Integer collId);
}
