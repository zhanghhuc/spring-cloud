package com.example.shiro.filter;


import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhang on 2017/8/17.
 */
public class FormFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(FormFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("customer form filter .....");
        System.out.println(this.getPathWithinApplication(request));
        if(this.pathsMatch(this.getLoginUrl(), request)){
            //是登陆url
            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }

        HttpServletResponse res = (HttpServletResponse) response;
        //判断是否含有token
        //token合法就放行
        String token = request.getParameter("token");
        if("token".equals(token)){
            return true;
        }else {
            if(log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }
            res.setHeader("Content-type", "text/html;charset=UTF-8");
            res.getWriter().print("没有登录");
            res.flushBuffer();
            return false;
        }
    }
}
