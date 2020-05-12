package com.yijv.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yijv.dao.IHouseDao;
import com.yijv.pojo.HouseView;
import com.yijv.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private IHouseService houseService;

    @RequestMapping("/findFourHouse.do")
    public ModelAndView findFourHouse(){
        //根据service 分别查询当前房源所有信息
        List<HouseView> newHouses = this.houseService.findFourHouse(0);
        List<HouseView> oldHouses = this.houseService.findFourHouse(1);
        List<HouseView> rentHouses = this.houseService.findFourHouse(2);


        //响应数据并响应页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("newHouses",newHouses);
        mv.addObject("oldHouses",oldHouses);
        mv.addObject("rentHouses",rentHouses);
        mv.setViewName("../main");
        return mv;
    }

    @RequestMapping("/findAllHouseByHouseType.do")
    public ModelAndView findAllHouseByHouseType(@RequestParam(defaultValue = "1") Integer page,
                                                Integer houseType){
        //调用Service方法，查出想要的信息
        List<HouseView> listHouses = houseService.findAllHouseByHouseType(page,houseType);
        //推荐房源
        List<HouseView> tjfy = houseService.findFourHouse(houseType);
        PageInfo<HouseView> pi = new PageInfo<>(listHouses);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pi",pi);
        mv.addObject("tjfy",tjfy);
        mv.setViewName("houses");
        return mv;
    }

    @RequestMapping("/findHouseById.do")
    public ModelAndView findHouseById(Integer houseId){
        HouseView house = houseService.findHouseById(houseId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("houseInfo",house);
        mv.setViewName("details");
        return mv;
    }
}
