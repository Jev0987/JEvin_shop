package com.JEvin.service;

import com.JEvin.pojo.ContentCategory;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service
 *  @文件名:   ContentCategoryService
 *  @创建者:   85169
 *  @创建时间:  2018/10/30 9:26
 *  @描述：    TODO
 */
public interface ContentCategoryService {
    List<ContentCategory> list(long parentId);

    ContentCategory add(String name,long parentId);

    int update(ContentCategory contentCategory);

    int delete(ContentCategory contentCategory);
}
