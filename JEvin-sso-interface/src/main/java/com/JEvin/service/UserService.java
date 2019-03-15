package com.JEvin.service;

import com.JEvin.pojo.User;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service
 *  @文件名:   UserService
 *  @创建者:   85169
 *  @创建时间:  2018/11/14 9:00
 *  @描述：    TODO
 */
public interface UserService {
    /**
     * 校验用户名|邮箱|电话号码是否可用
     * @param param
     * @param type
     * @return ture 可用，  false 不可用
     */
    Boolean check(String param,int type);

    /**
     * 根据ticket查找用户
     * @param ticket
     * @return
     */
    String selectUserByticket(String ticket);

    /**
     * 执行注册功能
     * @param user
     * @return
     */
    int register(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    String login(User user);

    /**
     *
     * @param ticket
     * @return
     */
    User findUser(String ticket);
}
