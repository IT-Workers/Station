package com.unitmb.station.view.controller;

import com.unitmb.station.view.service.ViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class ViewController {

    @Resource
    private ViewService viewService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("head_nav_menu", viewService.init());
        model.addAttribute("tt_news", viewService.getListNews("TT"));
        return "index";
    }

    @GetMapping("news/{id}")
    public String news(@PathVariable String id, Model model){
        model.addAttribute("news", viewService.getNewsContent(id));
        return "news";
    }

    @GetMapping("news/list")
    public String newsList(String name, Model model){
        model.addAttribute("newsList", viewService.getNewsListByName(name));
        return "newsList";
    }

}
