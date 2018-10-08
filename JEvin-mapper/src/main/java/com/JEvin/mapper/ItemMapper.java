package com.JEvin.mapper;

import com.JEvin.pojo.Item;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ItemMapper extends Mapper<Item> {
    int addItem(Item name);

    int updateItem(Item item);

    int deleteById(Long id);

    List<Item> getAllItem();
}
