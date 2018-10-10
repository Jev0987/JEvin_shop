package com.JEvin.service;

import com.JEvin.pojo.User;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service
 *  @文件名:   UserService
 *  @创建者:   85169
 *  @创建时间:  2018/10/8 14:37
 *  @描述：    TODO
 */
public interface UserService {

        void save();

        /*
        查询所有用户
         */
        List<User> selectAll();
    }
