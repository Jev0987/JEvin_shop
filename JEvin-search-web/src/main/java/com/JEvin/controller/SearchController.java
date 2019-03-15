package com.JEvin.controller;

import com.JEvin.Service.SearchService;
import com.JEvin.pojo.Item;
import com.JEvin.pojo.Page;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   SearchController
 *  @创建者:   85169
 *  @创建时间:  2018/11/27 9:21
 *  @描述：    TODO
 */
@Controller
public class SearchController {

    @Reference
    private SearchService searchService;

    @RequestMapping("search.shtml")
    public String searcch(String q,@RequestParam(defaultValue = "1") int page,Model model){

        int pageSize = 16;

        System.out.println("要跳转到搜索页面了");

        //从索引库查询数据
        Page<Item> pageInfo = searchService.search(q, page, pageSize);

        //把数据保存到model里面去，以便到页面显示
        model.addAttribute("page" , pageInfo);
        model.addAttribute("query" , q);
        model.addAttribute("totalPages" , pageInfo.getLast());


        return "search";
    }
}
