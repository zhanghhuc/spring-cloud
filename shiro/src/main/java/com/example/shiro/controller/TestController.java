package com.example.shiro.controller;

import com.alibaba.fastjson.JSON;
import com.example.shiro.UserRealm;
import com.example.shiro.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zhang on 2017/8/16.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PermissionService permissionService;
    @Autowired
    public UserRealm userRealm;

    @RequestMapping("getP")
    @ResponseBody
    public  String getPermission(){

        Subject user = SecurityUtils.getSubject();
        Session session = user.getSession();
        System.out.println("session = "+session);
        System.out.println(""+user.getPrincipal());
        System.out.println("session = "+user.isAuthenticated());
        System.out.println("session = "+session);

        Map<String, Object> all = permissionService.selectAll();
        String str = JSON.toJSONString(all, true);
        return  str;
    }

    @RequestMapping("/login")
    public String login(String name,String pwd){
        try {
            System.out.println("name = "+name);
            System.out.println("pwd = "+pwd);
            UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + name + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + name + "]进行登录验证..验证通过");

            return "index";
        } catch (AuthenticationException e) {
            return "403";
        }
    }



}
