package com.JEvin.service;

import com.JEvin.pojo.ItemCat;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service
 *  @文件名:   ItemCatService
 *  @创建者:   85169
 *  @创建时间:  2018/10/11 10:40
 *  @描述：    TODO
 */
public interface ItemCatService {

    List<ItemCat> getCategoryByParentId(int parentId);
}
