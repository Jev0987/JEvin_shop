package com.JEvin.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin
 *  @文件名:   SearchAPP
 *  @创建者:   85169
 *  @创建时间:  2018/11/27 9:19
 *  @描述：    TODO
 */
@SpringBootApplication
@MapperScan("com.JEvin.mapper")
public class SearchServiceApp {
    public static void main(String [] args){
        SpringApplication.run(SearchServiceApp.class,args);
    }
}
