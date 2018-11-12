package com.JEvin.service.impl;

import com.JEvin.mapper.ItemCatMapper;
import com.JEvin.pojo.ItemCat;
import com.JEvin.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service.impl
 *  @文件名:   ItemCatServiceImpl
 *  @创建者:   85169
 *  @创建时间:  2018/10/11 10:41
 *  @描述：    TODO
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;


    @Override
    public List<ItemCat> getCategoryByParentId(int parentId) {
        //itemCatMapper.selectAll();
        //itemCatMapper.selectByPrimaryKey(1);

        //按照普通列来查询。
        ItemCat itemCat = new ItemCat();

        Long id = Long.parseLong(parentId+"");

        //long val = Long.parseLong(parentId+"");
        itemCat.setParentId(id);

        //selectByExample 里面使用的是criteria 查询条件对象。
        List<ItemCat> list = itemCatMapper.select(itemCat);

       /* Example example = new Example(ItemCat.class);
        example.createCriteria().andEqualTo("username","zhangsan");
        itemCatMapper.selectByExample(example);*/

        //想按照学生的姓名来查询
        /*Student  stu = new Student();
        stu.setName("张三")
        itemCatMapper.select(Student);*/

        return list;
    }
}
