package com.JEvin.controller;

import com.JEvin.pojo.User;
import com.JEvin.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   UserController
 *  @创建者:   85169
 *  @创建时间:  2018/11/17 16:35
 *  @描述：    用户相关的controller
 */
@Controller
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/user/doRegister")
    @ResponseBody
    public Map<String , Integer> register(User user){

        Map<String , Integer> map = new HashMap<String ,Integer>();

        int result = userService.register(user);
            if(result > 0){
            map.put("status",200);
        }else{
            map.put("status",500);
        }
        return map;
    }

    @RequestMapping("/user/doLogin")
    @ResponseBody
    public Map<String ,String> login(User user, HttpServletResponse response){

        Map<String ,String> map = new HashMap<String , String>();

        String ticket = userService.login(user);

        if(ticket != null){
            //登陆成功！
            Cookie cookie = new Cookie("ticket",ticket);

            cookie.setMaxAge(60*60*24*7);

            cookie.setPath("/");//让cookie可以在根目录下演示，即www.jvshop.com/下

            response.addCookie(cookie);


            map.put("status","200");
            map.put("success" ,"http://www.jvshop.com");

        }

        return map;
    }

}
