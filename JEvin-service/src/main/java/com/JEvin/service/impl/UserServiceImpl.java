package com.JEvin.service.impl;

import com.JEvin.mapper.UserMapper;
import com.JEvin.pojo.User;
import com.JEvin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service.impl
 *  @文件名:   UserServiceImpl
 *  @创建者:   85169
 *  @创建时间:  2018/10/8 14:44
 *  @描述：    TODO
 */
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void save() {
        System.out.println("调用了UserServiceImpl的save方法");
    }

    @Override
    public List<User> selectAll() {

        //service 肯定要去调用mapper

        return userMapper.selectAll();

    }
}
