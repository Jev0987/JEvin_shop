package com.JEvin.controller;

import com.JEvin.pojo.Item;
import com.JEvin.service.ItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.controller
 *  @文件名:   IndexController
 *  @创建者:   85169
 *  @创建时间:  2018/11/28 10:31
 *  @描述：    TODO
 */
@RestController
public class IndexController {

    @Autowired
    private SolrClient solrClient;

   @Reference
    private ItemService itemService;

    @RequestMapping("initIndex")
    public String initIndex() throws Exception {

        int page = 1, rows = 500;

        do{

            List docList = new ArrayList();

            PageInfo<Item> pageInfo = itemService.list(page, rows);

            //得到了那500条数据 500 -500  -----300;
            List<Item> list = pageInfo.getList();

            for (Item item :list){

                //遍历一件商品，就得到一个solr的文档对象
                //一个文档对象就是一条索引数据。有500件商品就有500个索引数据，也就是有500个文档对象
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("id", item.getId());
                doc.addField("item_title", item.getTitle());
                doc.addField("item_price", item.getPrice());
                doc.addField("item_image", item.getImage());
                doc.addField("item_cid", item.getCid());
                doc.addField("item_status", item.getStatus());

                docList.add(doc);

            }
            solrClient.add(docList);

            //提交
            solrClient.commit();

            page++;
            rows = list.size();

        }while(rows == 500);


        return "success~!~";
    }
}
