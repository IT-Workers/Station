package com.unitmb.station.view.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unitmb.station.view.service.ViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController {

    @Resource
    private ViewService viewService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("head_nav_menu", viewService.init());
        model.addAttribute("tt_news", viewService.getListNews("TT"));
        model.addAttribute("randnews", viewService.getRandNews());
        model.addAttribute("randnews2", viewService.getRandNews());
        return "index";
    }

    @GetMapping("news/{id}")
    public String news(@PathVariable String id, Model model){
        model.addAttribute("head_nav_menu", viewService.init());
        Map<String, Object> data = viewService.getNewsContent(id);
        model.addAttribute("title", data.get("title"));
        model.addAttribute("content", data.get("content"));
        model.addAttribute("comment", JSON.parseArray(data.get("comment").toString()));
        model.addAttribute("randnews", viewService.getRandNews());
        model.addAttribute("randnews2", viewService.getRandNews());
        return "news";
    }

    @GetMapping("news/list")
    public String newsList(String name, Model model){
        model.addAttribute("head_nav_menu", viewService.init());
        model.addAttribute("newsList", viewService.getNewsListByName(name));
        model.addAttribute("name", name);
        model.addAttribute("randnews", viewService.getRandNews());
        model.addAttribute("randnews2", viewService.getRandNews());
        return "newsList";
    }

}
