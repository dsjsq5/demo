package com.example.demo.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value = "/demo")
public class UserController {
    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    String index(){
        return "index.html";
    }
    
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String test(){
        return "test1.html";
    }
}
