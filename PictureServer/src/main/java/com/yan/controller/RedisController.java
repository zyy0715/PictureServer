package com.yan.controller;

import com.yan.pojo.Test;
import com.yan.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: farben
 * @Date: 2020/12/28 15:57
 * @Description: Redis测试类
 */

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping("/string")
    public Object getRedisString(){
        Test test = new Test(2,  "这是一个字符串" );
        redisUtil.setString("string",test);
        return redisUtil.getString("string");
    }

    @RequestMapping("/list")
    public Object getRedisList(){
        List<String> list = new ArrayList<>();
        list.add("测试字符串1");
        list.add("测试字符串2");
        list.add("测试字符串3");
        list.add("测试字符串4");
        redisUtil.setString("list", list);
        Object result;
        result = redisUtil.getString("list");
        return result;
    }

    @RequestMapping("/map")
    public Object getRedisMap(){
        Map<String,String> map = new HashMap<>();
        map.put("map1", "测试字符串1");
        map.put("map2", "测试字符串2");
        map.put("map3", "测试字符串3");
        map.put("map4", "测试字符串4");
        redisUtil.setString("map", map);
        Object result;
        result = redisUtil.getString("map");
        return result;
    }

    @RequestMapping("/object")
    public Object getRedisObject(){
        Test test = new Test(1, "张三");
        redisUtil.setString("object", test);
        Object result;
        result = redisUtil.getString("object");
        return result;
    }

}
