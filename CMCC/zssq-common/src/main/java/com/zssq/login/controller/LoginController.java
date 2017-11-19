package com.zssq.login.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.zssq.utils.PropertiesUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Login;
import com.zssq.pojo.LoginInfo;
import com.zssq.pojo.Logout;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.pojo.Ticket;

import redis.clients.jedis.Jedis;

/**
 * 员工登陆认证
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
//@Controller
//@RequestMapping("/common")
public class LoginController {

	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());

	private Properties propsRedis = new PropertiesUtil("ssoRedis.properties");

	/**
	 * 单点注销
	 * 
	 * @param param
	 * 			封装操作所需参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public ResultJSON logout(@RequireValid Logout param) throws BusinessException {
		
		Message m = null;
		ResultJSON rj = new ResultJSON();
		try {
			String isExistData = param.getToken();
			
			@SuppressWarnings("resource")
			Jedis redis = new Jedis(propsRedis.getProperty("webRedisIP"),
					Integer.valueOf(propsRedis.getProperty("webRedisPort"))); 
			redis.auth(propsRedis.getProperty("webRedisPwd"));
			redis.del(isExistData);
			
			m = PropertiesUtil.getMessage("COMMON_200");
			rj.setReturnCode(m.getCode());
			rj.setReturnTip(m.getTip());
			rj.setBody(new JSONObject());

			log.info("单点注销成功，" + isExistData);
		} catch (Exception e) {
			log.info("单点注销出错", e);
			throw BusinessException.build("SSO_0001", "注销");
		}
		return rj;
	}

	/**
	 * 员工登陆入口
	 * 
	 * @param request
	 * 			http 请求对象
	 * @param param
	 * 			封装操作所需参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResultJSON login(HttpServletRequest request, @RequireValid Login param)
			throws BusinessException {
		
		ResultJSON rj = new ResultJSON();
		try {
			// 获取票据信息，该信息有 SSOInterceptor 拦截器逻辑处理
			Object ssoObj = request.getAttribute("ticketMsg");
			if (ssoObj != null) {
				ResultJSON resultJSON = JSONObject.parseObject(String.valueOf(ssoObj), ResultJSON.class);
				if (resultJSON.getReturnCode().equals("200")) { 
					Ticket t = JSONObject.parseObject(String.valueOf(resultJSON.getBody()), Ticket.class);
					rj.setReturnCode(resultJSON.getReturnCode());
					rj.setReturnTip(resultJSON.getReturnTip());
					LoginInfo login = new LoginInfo();
					login.setUserCode(param.getUserCode());
					login.setApp(param.getApp());
					login.setToken(t.getToken());

					rj.setBody(login);
					log.info("单点登录成功：" + param.getUserCode() + "-" + param.getApp());
				}
			}
		} catch (Exception e) {
			log.error("单点登陆成功", e);
			throw BusinessException.build("SSO_0001", "登录");
		}
		return rj;
	}
}
