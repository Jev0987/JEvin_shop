package com.JEvin.service;

import com.JEvin.pojo.Cart;

import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service
 *  @文件名:   CartService
 *  @创建者:   85169
 *  @创建时间:  2018/12/26 16:09
 *  @描述：    TODO
 */
public interface CartService {

    /**
     * 添加商品的购物车
     * @param userId 用户id
     * @param itemId 商品id
     * @param num 购买数量
     */
    void addToCart(long userId , long itemId , int num);



    /**
     * 根据用户id查询对应的购物车商品，一个用户的购物车可以有多件商品，所以返回值是list
     * @param userId
     */
    List<Cart> queryCartByUserId(long userId);

    /**
     * 更新购物车的数量
     * @param userId
     * @param itemId
     * @param num
     */
    void updateNumByCart(long userId , long itemId , int num);


    /**
     * 删除购物车商品
     * @param itemId
     */
    void deleteItemByCartlong (long userId , long itemId );



}
