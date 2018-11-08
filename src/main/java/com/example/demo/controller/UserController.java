package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
//@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
//@RestController  //如果要用restful接口，必须使用该注解
@RequestMapping(value = "/demo")
public class UserController {
    @Value("${a.name}")
    public String name;

    @Resource(name="userDao")
    UserDao userDao;
    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
//        System.out.println(name);
        User user = new User();
        user.setId("1");
        User userTemp = userDao.getUserById(user);
        System.out.println(userTemp.getName());
        return "index";
    }
    
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test1.html";
    }
}
