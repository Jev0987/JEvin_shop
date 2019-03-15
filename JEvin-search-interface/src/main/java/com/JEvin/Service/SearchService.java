package com.JEvin.Service;

import com.JEvin.pojo.Item;
import com.JEvin.pojo.Page;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   SearchService
 *  @创建者:   85169
 *  @创建时间:  2018/12/2 13:45
 *  @描述：    TODO
 */
public interface SearchService {

    Page<Item> search(String q, int page, int pageSize);

    void addItem(String message);


}
