package com.JEvin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin
 *  @文件名:   ManagerApp
 *  @创建者:   85169
 *  @创建时间:  2018/9/10 10:07
 *  @描述：    TODO
 */
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class ManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class,args);
    }
}