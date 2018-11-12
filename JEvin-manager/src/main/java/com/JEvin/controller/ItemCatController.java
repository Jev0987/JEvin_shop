package com.JEvin.controller;

import com.JEvin.pojo.ItemCat;
import com.JEvin.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   ItemCatController
 *  @创建者:   85169
 *  @创建时间:  2018/10/11 10:37
 *  @描述：    TODO
 */
@Controller
public class ItemCatController {

    //@Reference

    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/rest/item/cat")
    @ResponseBody
    public List<ItemCat> getCategoryByParentId(@RequestParam(defaultValue = "0") int id){


//        System.out.println("标记，使用了getCategoryByParentId方法");

        //默认先获取所有的一级分类

        //int parentId = 0;

        List<ItemCat> list = itemCatService.getCategoryByParentId(id);

        return list;
    }
}
