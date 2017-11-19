package com.example.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhang on 2017/8/16.
 */
@Controller
@RequestMapping("/page")
public class ToPageController {

    @RequestMapping("/{pageName}")
    public String toPage(@PathVariable("pageName") String pageName){
        System.out.println("跳转页面 controller......");
        System.out.println(pageName);
        return pageName;
    }
}
