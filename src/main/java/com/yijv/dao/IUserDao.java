package com.yijv.dao;

import com.yijv.bean.Collect;
import com.yijv.bean.UserAuth;
import com.yijv.bean.UserInfo;
import com.yijv.pojo.CollectView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库交互层
 */
public interface IUserDao {

    //登录
    UserInfo findUserByPhone(String phone);

    void addUser(@Param("phone") String phone,@Param("password") String password);

    void saveEdit(UserInfo userInfo);

    UserAuth findUserAuthById(Integer userId);

    void saveAuth(UserAuth userAuth);

    void deleteAuthById(Integer userId);

    void updPassword(UserInfo user);

    List<CollectView> getCollectViewById(Integer userId);

    Collect findCollectById(@Param("userId") Integer userId, @Param("houseId") Integer houseId);

    void addCollect(Collect collect);

    void deleteCollect(Integer collId);
}
