package com.JEvin.freemarker;

import com.JEvin.pojo.Item;
import com.JEvin.pojo.ItemDesc;
import com.JEvin.service.ItemDescService;
import com.JEvin.service.ItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller.serive
 *  @文件名:   FreeMarkerService
 *  @创建者:   85169
 *  @创建时间:  2018/12/21 16:18
 *  @描述：    TODO
 */
@Component  //功能和service 差不多，就是把这个类交给spring 托管，后续会调用下面的方法
public class FreeMarkerService {

    @Reference
    private ItemService itemService;

    @Reference
    private ItemDescService itemDescService;

    //只要后台新增加了商品，那么就会执行这个方法，用于创建商品的详情页
    @JmsListener(destination = "item_save")
    public void addItem(String message) throws Exception {

        System.out.println("商品详情页收到的消息是：" + message);


        Item item = itemService.findItemById(Long.parseLong(message));
        ItemDesc itemDesc = itemDescService.findDescById(Long.parseLong(message));


        //创建配置对象
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);


        //C:\Users\85169\IdeaProjects\JEvin-shop\JEvin-item-web\src\main\webapp

        String path =System.getProperty("user.dir")+"/src/main/webapp/ftl";
        //设置加载模板的文件路径
        configuration.setDirectoryForTemplateLoading(new File(path));

        //获取模板
        Template template = configuration.getTemplate("item.ftl");

        //准备数据
        Map<String , Object> root = new HashMap<>();

        root.put("item",item);
        root.put("itemDesc",itemDesc);

        //静态页面输出的位置
        Writer out = new FileWriter("D:/jvshop/item/"+message+".html");

        //输出html页面
        template.process(root,out);

        out.close();
    }
}
