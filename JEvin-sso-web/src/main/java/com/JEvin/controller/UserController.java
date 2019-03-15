package com.JEvin.controller;

import com.JEvin.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   UserController
 *  @创建者:   85169
 *  @创建时间:  2018/11/14 10:11
 *  @描述：    TODO
 */
@Controller
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public String check(@PathVariable String param, @PathVariable int type, String callback){

        Boolean flag = userService.check(param, type);

        System.out.println(flag ? "可以使用" : "不能使用 ");

        String result = callback+"("+flag+")";

        return result;
    }

    @RequestMapping("/user/{ticket}")
    @ResponseBody
    public String selectUserByTicket(@PathVariable String ticket){

        System.out.println("根据用户ticket查询用户信息");

        String json = userService.selectUserByticket(ticket);

        System.out.println("json=" + json);

        return json;
    }
}
