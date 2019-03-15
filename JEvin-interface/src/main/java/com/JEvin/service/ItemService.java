package com.JEvin.service;

import com.JEvin.pojo.Item;
import com.github.pagehelper.PageInfo;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service
 *  @文件名:   ItemService
 *  @创建者:   85169
 *  @创建时间:  2018/10/15 11:31
 *  @描述：    TODO
 */

public interface ItemService {

    /**
     * 添加商品
     * @param item 商品基本信息
     * @param desc 商品的描述
     * @return
     */

    int add(Item item,String desc);

    PageInfo<Item> list(int page,int rows);

    Item findItemById(long id);

    int deleteItem(long id);

    int updateItem(Item item);
}
