package com.unitmb.station.admin.controller;

import com.unitmb.station.admin.model.HeadNavMenu;
import com.unitmb.station.admin.service.AdminService;
import com.unitmb.station.common.controller.CommonController;
import com.unitmb.station.view.service.ViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class AdminController extends CommonController{

    @Resource
    private AdminService adminService;

    @Resource
    private ViewService viewService;

    @GetMapping("")
    public String admin(Model model){
        model.addAttribute("head_nav_menu", viewService.init());
        model.addAttribute("tt_news", viewService.getListNews("TT"));
        return "admin";
    }

    @PostMapping("/add/head-nav-menu")
    public void addHeadNavMenu(String name){
        HeadNavMenu menu = new HeadNavMenu();
        menu.setId(System.currentTimeMillis());
        menu.setName(name);
        menu.setHref("www.baidu.com");

        if(adminService.addHeadNavMenu(menu)){
            success();
        }else{
            fail();
        }
    }

    @PostMapping("/edit/head-nav-menu")
    public void editHeadNavMenu(long id, String name){
        if(adminService.updateHeadNavMenu(id, name)){
            success();
        }else{
            fail();
        }
    }

    @PostMapping("/del/head-nav-menu")
    public void delHeadNavMenu(long id, String name){
        if(adminService.delHeadNavMenu(id, name)){
            success();
        }else{
            fail();
        }
    }


}
