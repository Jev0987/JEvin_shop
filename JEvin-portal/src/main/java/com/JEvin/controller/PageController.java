package com.JEvin.controller;

import com.JEvin.pojo.User;
import com.JEvin.service.ContentService;
import com.JEvin.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   PageController
 *  @创建者:   85169
 *  @创建时间:  2018/10/29 9:49
 *  @描述：    TODO
 */
@Controller
public class PageController {

    @Reference
    private ContentService contentService;

    @Reference
    private UserService userService;

    @RequestMapping("/page/{pageName}")
    public String page(@PathVariable String pageName){

        System.out.println("pageName=" + pageName);

        return pageName;
    }

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){

        //这里要查询数据库， 然后在首页显示动态内容。
        System.out.println("要跳转首页了~!~!");


        //获取令牌ticket
        Cookie[] cookies = request.getCookies();

        if(cookies != null){

            for(Cookie cookie:cookies){
                String name = cookie.getName();
                System.out.println("name=" + name+"="+cookie.getValue());
                if("ticket".equals(name)){
                    //得到了cookie
                    String value = cookie.getValue();
                    User user = userService.findUser(value);

                    System.out.println("user=" + user);
                    model.addAttribute("user",user);

                    break;
                }
            }
        }


        //如果首页的内容要动态显示，那么就需要在这个方法里面写代码了。

        //可以取 大广告位 |  淘淘快报 | 边栏广告

        long categoryId = 89;

        //如果要做缓存的话，这一批代码要放到service去写，然后查询出来，自己组装成json

        //数据，接着存到redis去，并且给controller返回json

        //不能直接返回这个list集合
        String json = contentService.getContentByCategoryId(categoryId);


        model.addAttribute("data",json);

        return "index";

    }
}
