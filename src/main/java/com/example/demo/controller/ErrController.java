package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ErrController implements org.springframework.boot.web.servlet.error.ErrorController {

    /* 错误页面配置
    * Spring Boot 将所有的错误默认映射到/error， 实现ErrorController
    * */
    @RequestMapping(value = "/error")

    public String error(){
        System.out.println("error123:123");
        return "redirect:/demo/index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
