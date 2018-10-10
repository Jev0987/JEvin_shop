package com.JEvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   PageController
 *  @创建者:   85169
 *  @创建时间:  2018/10/8 11:29
 *  @描述：    TODO
 */
@Controller
public class PageController {

    @RequestMapping("index")
    public String index(){
        System.out.println("要跳转首页了");

        //springboot里，默认的资源路径是在resources/static/ public..
        //由于我们能的网页是在web-inf/view路径内的，所以要告诉springMVC资源路径的位置在哪里

        return "index";
    }

    //{pageName} 用于截取了 /rest/page/后面的字符
    //截取熬了之后，会赋值给pageName参数，注意：参数前面一定要加上@PathVariable注解
    //并且参数名称和{}内的名称要一致
    @RequestMapping("/rest/page/{pageName}")
    public String page(@PathVariable String pageName){

        System.out.println("pageName" + pageName);


        return pageName;

    }
}
