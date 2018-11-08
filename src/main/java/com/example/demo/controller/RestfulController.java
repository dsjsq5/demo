package com.example.demo.controller;

import com.example.demo.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@Controller
//@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
@RestController  //如果要用restful接口，必须使用该注解
@RequestMapping(value = "/restful")
public class RestfulController {

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String test1(@RequestBody User user) throws JsonProcessingException {
        ObjectMapper jsonMap = new ObjectMapper();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user",user);
        String str = jsonMap.writeValueAsString(map);
        return str;

    }
}
