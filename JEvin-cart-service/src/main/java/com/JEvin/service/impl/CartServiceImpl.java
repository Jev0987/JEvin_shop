package com.JEvin.service.impl;

import com.JEvin.pojo.Cart;
import com.JEvin.pojo.Item;
import com.JEvin.service.CartService;
import com.JEvin.service.ItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service.impl
 *  @文件名:   CartServiceImpl
 *  @创建者:   85169
 *  @创建时间:  2018/12/26 16:13
 *  @描述：    TODO
 */
@Service
public class CartServiceImpl implements CartService {

    @Reference
    private ItemService itemService;

    @Autowired
    private RedisTemplate<String,String > template;

    @Override
    public void addToCart(long userId, long itemId, int num) {

        //1.根据itemid查询要添加的商品数据
        Item item = itemService.findItemById(itemId);

        //2.根据userid， 去查询redis数据库，遍历购物车，判断是否有重复数据，进而修改数量
        //如果没有，就算是全新添加的商品
        //template.opsForValue().get("");
        List<Cart> cartList = queryCartByUserId(userId);

        Cart c = null;
        //3.遍历购物车 这个for循环目的就是为了找出来有没有重复的商品
        for (Cart cart :cartList){
            if (itemId == cart.getItemId()){
                //表示购物车里面有现在要添加的商品
                c = cart;
                break;
            }
        }
        if (c != null){
            //有重复的商品
            c.setNum(c.getNum() + num);
        }else {

            //没有重复的商品
            Cart cart = new Cart();
            cart.setItemId(itemId);
            cart.setItemTitle(item.getTitle());
            cart.setItemImage(item.getImages()[0]);
            cart.setItemPrice(item.getPrice());
            cart.setCreate(new Date());
            cart.setUpdate(new Date());
            cart.setNum(num);

            cartList.add(cart);
        }

        //4. 再把购物车添加到redis中。
        String json = new Gson().toJson(cartList);
        template.opsForValue().set("jv_"+userId,json);

    }

    @Override
    public List<Cart> queryCartByUserId(long userId) {


        //根据用户id，从redis中获取该用户的购物车数据，回来还是json字符串
        String json = template.opsForValue().get("jv_"+userId);

        //new Gson().fromJson(json,List.class);
        List<Cart> list = new Gson().fromJson(json,new TypeToken<List<Cart>>(){}.getType());

        //第一次来的时候，redis里面购物车是没有的，所以这个list集合就是null
        if (list == null){
            list = new ArrayList<>();
        }

        return list;
    }

    @Override
    public void updateNumByCart(long userId, long itemId, int num) {

        //1.根据用户id先查出来这个用户的购物车数据

        //根据用户id，从redis中获取该用户的购物车数据，回来还是json字符串
        String json = template.opsForValue().get("jv_"+userId);

        List<Cart> list = new Gson().fromJson(json,new TypeToken<List<Cart>>(){}.getType());

        //2.遍历购物车的数据，找到需要更新数量的商品，然后修改数量
        for (Cart cart : list) {
            if (itemId == cart.getItemId()){

                cart.setNum(num);
                cart.setUpdate(new Date());//设置购物车更新时间
                break;
            }

        }

        //3.再把数据回填到redis里面去
        json = new Gson().toJson(list);

        template.opsForValue().set("jv_"+userId, json);
    }

    @Override
    public void deleteItemByCartlong(long userId , long itemId) {

        //1.先查询购物车的数据
        String json = template.opsForValue().get("jv_"+userId);

        List<Cart> list = new Gson().fromJson(json,new TypeToken<List<Cart>>(){}.getType());


        //2.移除购物车数据
        for (Cart cart : list) {
            if (cart.getItemId() == itemId){
                list.remove(cart);
                break;
            }

        }

        //3.重新存到redis中.
        json = new Gson().toJson(list);

        template.opsForValue().set("jv_"+userId,json);


    }

}
