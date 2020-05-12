package com.yijv.controller;

import com.yijv.bean.Collect;
import com.yijv.bean.UserAuth;
import com.yijv.bean.UserInfo;
import com.yijv.pojo.CollectView;
import com.yijv.pojo.HouseView;
import com.yijv.service.IUserService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    private String doLogin(@Param("phone") String phone,
                           @Param("password") String password,
                           HttpSession session){
        //1.如果查不到用户时，提示注册
        //2.查到用户时，但是密码不对        -------         重新登录
        //3.密码正确时，登录成功            -------         登录成功
        JSONObject jsonObject = new JSONObject();
        UserInfo user = this.userService.findUserByPhone(phone);
        if (user == null){
            System.out.println("用户不存在");
            jsonObject.put("result","0");
        }else if (!password.equals(user.getPassword())){
            System.out.println("密码错误");
            jsonObject.put("result","1");
        }else {
            System.out.println("登录成功");
            //把当前的信息，保存到session
            session.setAttribute("user",user);
            jsonObject.put("result","2");
        }
        return jsonObject.toString();
    }

    @RequestMapping("/signup.do")
    @ResponseBody
    //注册signup.do
    public String signup(@Param("phone") String phone,
                         @Param("password") String password,
                         HttpSession session){
        //1.首先判断数据库中有没有这条数据
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo = userService.findUserByPhone(phone);
        if (userInfo != null){
            System.out.println("该用户已经注册");
            jsonObject.put("result","0");
        }else {
            //正常注册
            userService.addUser(phone,password);
            jsonObject.put("result","1");
        }
        return jsonObject.toString();
    }

    //修改个人信息saveEdit.do
    @RequestMapping("saveEdit.do")
    @ResponseBody
    public String saveEdit(UserInfo userInfo,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        this.userService.saveEdit(userInfo);
        session.setAttribute("user",userInfo);
        return jsonObject.toString();
    }

    @RequestMapping("/verify.do")
    public ModelAndView verify(HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        UserAuth userAuth = userService.findUserAuthById(user.getUserId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuth",userAuth);
        mv.setViewName("verify");
        return mv;
    }


    //saveAuth.do 添加认证
    @RequestMapping("/saveAuth.do")
    @ResponseBody
    public String saveAuth(UserAuth userAuth,HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        userAuth.setUserName(user.getTruename());
        userAuth.setUserGender(user.getGender());
        userAuth.setUserId(user.getUserId());
        userAuth.setCreateTime(new Date().getTime());
        userAuth.setUpdateTime(new Date().getTime());
        JSONObject jsonObject = new JSONObject();
        userService.saveAuth(userAuth);
        return jsonObject.toString();
    }

    //deleteAuthById.do删除认证
    @RequestMapping("/deleteAuthById.do")
    public String deleteAuthById(HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        userService.deleteAuthById(user.getUserId());
        return "personal";
    }

    //updPassword.do修改密码
    @RequestMapping("/updPassword.do")
    @ResponseBody
    public String updPassword(@RequestParam("oldpassword") String oldpassword,
                              @RequestParam("password") String password,
                              HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        JSONObject jsonObject = new JSONObject();
        if (!oldpassword.equals(user.getPassword())){
            jsonObject.put("result","0");
        }else {
            user.setPassword(password);
            userService.updPassword(user);
            jsonObject.put("result","1");
        }
        return jsonObject.toString();
    }

    //favorite.do
    @RequestMapping("/favorite.do")
    public ModelAndView favorite(HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        List<CollectView> collectViews = userService.getCollectViewById(user.getUserId());
        //响应数据并且响应页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("collectViews", collectViews);
        mv.setViewName("favorite");
        return mv;
    }

    @RequestMapping("/addCollect.do")
    @ResponseBody
    public String addCollect(HttpSession session,@RequestParam("houseId") Integer houseId){
        UserInfo user = (UserInfo) session.getAttribute("user");
        JSONObject jsonObject = new JSONObject();
        //1.先查询判断
//        System.out.println(user.getUserId());
        Collect c = userService.findCollectById(user.getUserId(),houseId);
        if (c != null){
            jsonObject.put("result","0");
        }else {
            Collect collect = new Collect();
            collect.setUserId(user.getUserId());
            collect.setHouseId(houseId);
            collect.setCreateTime(new Date().getTime());
            collect.setUpdateTime(new Date().getTime());
            userService.addCollect(collect);
            jsonObject.put("result","1");
        }
        return jsonObject.toString();
    }

    @RequestMapping("/deleteCollect.do")
    public String deleteCollect(Integer collId){
        userService.deleteCollect(collId);
        return "redirect:favorite.do";
    }
}
