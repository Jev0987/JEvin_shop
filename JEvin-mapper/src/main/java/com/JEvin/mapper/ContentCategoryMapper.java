package com.JEvin.mapper;

import com.JEvin.pojo.ContentCategory;
//使用了springboot
import tk.mybatis.mapper.common.Mapper;
//独立使用的，没有springboot
//import com.github.abel533.mapper.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface ContentCategoryMapper  extends Mapper<ContentCategory> {
}