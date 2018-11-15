package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
//    @RequiresRoles("admin")
    @RequiresPermissions("user")
    @RequestMapping(value = {"/index", ""}, method = RequestMethod.GET)
    public String index(Model model){
//        System.out.println(name);
        User user = new User();
        user.setId("1");
        User userTemp = userDao.getUserById(user);
        List<User> list = new ArrayList<User>();
        list.add(userTemp);
        model.addAttribute("list", list);
        System.out.println(userTemp.getName());
        return "index";
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = {"/testerr"}, method = RequestMethod.GET)
    public String testerr(){
        System.out.println("testerr----");
        return "login";
    }
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String test(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if(user != null){
            return "redirect:/demo/index";
        }
        return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/demo/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username,String password){
        System.out.println("123");
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        if (user != null){
            return "redirect:/demo/index";
        }


        return "redirect:/demo/login";
    }



    /* 错误页面配置*/
    @ExceptionHandler({ Exception.class })
    @ResponseStatus()
    public String error(){
        return "err";//跳转的页面不能是error，会报错，不知道为什么,也不能redirect:/demo/index
    }
}
