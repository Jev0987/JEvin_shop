package com.JEvin.controller;

import com.JEvin.pojo.User;
import com.google.gson.Gson;
import org.springframework.data.redis.core.RedisTemplate;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   RedisUtil
 *  @创建者:   85169
 *  @创建时间:  2018/12/27 10:46
 *  @描述：    TODO
 */
public class RedisUtil {

    public static User findUserByTicket(RedisTemplate<String ,String > template , String ticket){

        //从redis里获取了用户信息
        String json = template.opsForValue().get(ticket);

        //json ----->user对象
        return new Gson().fromJson(json, User.class);
    }

}
