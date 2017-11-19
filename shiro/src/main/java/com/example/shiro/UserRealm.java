package com.example.shiro;

import com.example.shiro.config.ShiroConfiguration;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhang on 2017/8/16.
 */
@Component
public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("userRealm 授权。。。");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("userRealm 登录。。。。");
        String name = (String) token.getPrincipal();
        String pwd = new String((char[]) token.getCredentials());
        System.out.println("realm  name="+name);
        System.out.println("realm  pwd="+pwd);
        if(name.endsWith("zhang") && pwd.equals("123456")){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name, pwd, "UserRealm");
            return info;
        }
        return null;
    }
}
