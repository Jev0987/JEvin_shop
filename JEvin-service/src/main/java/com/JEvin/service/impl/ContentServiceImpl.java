package com.JEvin.service.impl;

import com.JEvin.mapper.ContentMapper;
import com.JEvin.pojo.Content;
import com.JEvin.service.ContentService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  JEvin-shop 
 *  @包名：    com.JEvin.service.impl
 *  @文件名:   ContentServiceImpl
 *  @创建者:   85169
 *  @创建时间:  2018/11/5 14:36
 *  @描述：    TODO
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private ContentMapper contentMapper;

    //private RedisTemplate<String , String>redisTemplate;

    @Override
    public int add(Content content) {

        content.setCreated(new Date());
        content.setUpdated(new Date());

        int result  = contentMapper.insert(content);

        //只要有写入的操作，就立即清除缓存，以便下次能得到最新的数据
        //bigAd:ljdflkajlsjdlfjasjdfljalsdf

        //如果使用这种方式，key还是存在的，只是值是空字符串
        //redisTemplate.opsForValue().set("bigAd","");

        //redisTemplate.opsForValue().getOperations().delete("bigAd");
        return result;
    }

    @Override
    public PageInfo<Content> list(long categoryId, int page, int rows) {
        //1. 分页的设置
        PageHelper.startPage(page , rows);


        //
        Content content = new Content();
        content.setCategoryId(categoryId);
        List<Content> list = contentMapper.select(content);


        return new PageInfo<>(list);
    }

    @Override
    public int edit(Content content) {
        content.setUpdated(new Date());

        int result = contentMapper.updateByPrimaryKeySelective(content);


        //redisTemplate.opsForValue().getOperations().delete("bigAd");

        return result;
    }

    @Override
    public int delete(String ids) {
               /* Content c = new Content();
        c.setId(id);
    */
        int result = 0 ;
        for (String id : ids.split(",")) {
            result += contentMapper.deleteByPrimaryKey(Long.parseLong(id));
        }

        //redisTemplate.opsForValue().getOperations().delete("bigAd");

        return result ;
    }

   @Override
    public String getContentByCategoryId(long categoryId) {

        /*
            1. 先从redis查询，如果有，直接返回。
            2. redis没有，查询mysql
            3. 从mysql里面查询出来是一个  List<Content> 转化成json
            4. 存储到redis去，并且返回给controller
         */

       System.out.println("现在要获取大广告位的数据了");

       ValueOperations<String, String> ops = redisTemplate.opsForValue();
       String json = ops.get("bigAd");
       //如果json字符串不是空，表示redis里面有数据，直接返回就可以了。
       if(!StringUtils.isEmpty(json)){
           System.out.println("redis里面有数据，直接返回了");
           return json;
       }
       Content content = new Content();
        content.setCategoryId(categoryId);

        return contentMapper.select(content);


    }
}
