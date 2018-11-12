package com.JEvin.service.impl;

import com.JEvin.mapper.ItemDescMapper;
import com.JEvin.mapper.ItemMapper;
import com.JEvin.pojo.Item;
import com.JEvin.pojo.ItemDesc;
import com.JEvin.service.ItemService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service.impl
 *  @文件名:   ItemService
 *  @创建者:   85169
 *  @创建时间:  2018/10/15 11:32
 *  @描述：    TODO
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;
    /*
   cid: 560
   title: iphonexs
   sellPoint: 贵
   priceView: 12000.00  //在页面上显示的价格
   price: 1200000 //存到数据库。
   num: 10
   barcode:
   image:


   id : 商品id
   status :  商品状态
   created
   updated
    */
    @Override
    public int add(Item item,String desc) {

        Date time = new Date();
        long id = (long) (System.currentTimeMillis() + Math.random() *100000);
        item.setId(id);
        item.setStatus(1);
        item.setCreated(time);
        item.setUpdated(time);

        int result = itemMapper.insert(item);

        //以下代码是添加商品的描述

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(time);
        itemDesc.setUpdated(time);
        itemDesc.setItemId(id);
        itemDescMapper.insert(itemDesc);

        return result;
    }

    @Override
    public PageInfo<Item> list(int page, int rows) {

        PageHelper.startPage(page,rows);

       List<Item> list =  itemMapper.selectAll();


        return new PageInfo<>(list);
    }
}
