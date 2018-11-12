package com.JEvin.service;

import com.JEvin.pojo.Content;
import com.github.pagehelper.PageInfo;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service
 *  @文件名:   ContentService
 *  @创建者:   85169
 *  @创建时间:  2018/11/5 14:35
 *  @描述：    TODO
 */
public interface ContentService {
    int add(Content content);

    PageInfo<Content> list(long categoryId , int page , int rows);

    int edit(Content content);

    int delete(String ids);


    // List<Content> getContentByCategoryId(long categoryId);
    String getContentByCategoryId(long categoryId);
}
