package com.JEvin.controller;

import com.JEvin.pojo.Cart;
import com.JEvin.pojo.User;
import com.JEvin.service.CartService;
import com.JEvin.util.CookieUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   CartController
 *  @创建者:   85169
 *  @创建时间:  2018/12/26 16:05
 *  @描述：    TODO
 */
@Controller
public class CartController {

    @Reference
    private CartService cartService;

    @Autowired
    private RedisTemplate<String,String> template;

    @RequestMapping("/cart/add/{id}.html")
    public String addToCart(@PathVariable long id, int num, HttpServletRequest request){

        //回想以前的登录知识点，只要登录成功，那么用户的信息会存放在redis中
        //在用户的cookie里面保存了redis的key， 作为登录的凭证。
        String ticket = CookieUtil.findTicket(request);

        if(ticket != null){

            //从redis里获取了用户信息
           String json = template.opsForValue().get(ticket);

           //json ----->user对象
            User user = new Gson().fromJson(json, User.class);

            cartService.addToCart(user.getId(), id, num);

        }else{
            //没有登录  -- 就不能用redis ---购物车的商品嘚放到cookie里面


        }

        return "cartSuccess";
    }

    @RequestMapping("/cart/cart.html")
    public String show(HttpServletRequest request, Model model){

        String ticket = CookieUtil.findTicket(request);

        //1.先获取用户信息
        if (ticket != null){
            //从redis里获取了用户信息
            String json = template.opsForValue().get(ticket);

            //json ----->user对象
            User user = new Gson().fromJson(json, User.class);

            List<Cart> cartList = cartService.queryCartByUserId(user.getId());

            model.addAttribute("cartList",cartList);

        }else {
            //没有登录
        }
        //1.去redis中获取这个用户的给购物车数据

        return "cart";
    }

    /*
    由于上面给的注解是：@controller  ,所以所有的方法不管有返回值还是没有返回值
    都会被认为是跳转页面，如果有返回值就跳转具体的页面，没有返回值就调转 根目录/ 页面
     */
    @RequestMapping("service/cart/update/num/{id}/{num}")
    @ResponseBody
    public void updateNumByCart(@PathVariable long id , @PathVariable int num,HttpServletRequest request){

        String ticket = CookieUtil.findTicket(request);

        if(ticket != null){
            //从redis里获取了用户信息
            String json = template.opsForValue().get(ticket);

            //json ----->user对象
            User user = new Gson().fromJson(json, User.class);

            cartService.updateNumByCart(user.getId() , id,num);

        }else {
            //没有登录
        }

    }

    @RequestMapping("/cart/delete/{id}.shtml")
    public String deleteItemByCart(@PathVariable long id,HttpServletRequest request){

        String ticket = CookieUtil.findTicket(request);

        if (ticket != null){

            User user = RedisUtil.findUserByTicket(template , ticket);
            cartService.deleteItemByCartlong(user.getId(),id);
        }else {

        }

        //重新定位到购物车的方法
      return "redirect:/cart/cart.html";
    }
}

