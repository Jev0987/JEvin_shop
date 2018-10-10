package com.JEvin.controller;

import com.JEvin.pojo.User;
import com.JEvin.service.UserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   UserController
 *  @创建者:   85169
 *  @创建时间:  2018/9/10 10:08
 *  @描述：    TODO
 */
@RestController
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("save")
    public String save(){
        System.out.println("调用了UserController的save方法");

        userService.save();

        return "save scuccess!~!~!";
    }

    @RequestMapping("selectAll")
    public String selectAll(){
        System.out.println("调用了UserController的selectAll方法");
        List<User> list = userService.selectAll();
        for(User user :list){
            System.out.println("user= " + user);
        }

        return "selectAll scuccess!~!!~!";
    }

}
