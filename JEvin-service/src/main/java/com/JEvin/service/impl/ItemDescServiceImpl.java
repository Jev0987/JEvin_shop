package com.JEvin.service.impl;

import com.JEvin.mapper.ItemDescMapper;
import com.JEvin.pojo.ItemDesc;
import com.JEvin.service.ItemDescService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service.impl
 *  @文件名:   ItemDescServiceImpl
 *  @创建者:   85169
 *  @创建时间:  2018/12/17 14:11
 *  @描述：    TODO
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public ItemDesc findDescById(long id) {
        return itemDescMapper.selectByPrimaryKey(id);
    }
}
