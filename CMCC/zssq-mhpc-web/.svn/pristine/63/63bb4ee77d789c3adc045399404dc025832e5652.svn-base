package com.zssq.sso.controller;

import java.util.ArrayList;
import java.util.List;

import com.zssq.utils.DESUtil;
import com.zssq.utils.PropertiesUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.common.cache.client.JedisClusterService;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Login;
import com.zssq.pojo.Logout;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.sso.config.SmapConfig;

/**
 * 员工登陆认证
 * 
 * @since JDK 1.7
 * @author 赵翊
 */
@Controller
@RequestMapping("/common")
public class LoginController {

	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** redis 操作组件 */
	@Autowired
	private JedisClusterService jedisClusterService;
	
	/** Smap配置属性 */
	@Autowired
	private SmapConfig smapConfig;

	/**
	 * 员工登陆入口
	 * 
	 * @param param
	 *            封装操作所需参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResultJSON login(@RequireValid Login param) throws BusinessException {

		try {
			// 调用 sso-web 进行身份认证，分配票据
//			String requestUrl = "http://172.16.135.156:8081/zssq-sso-web/sso/certification";
			String requestUrl = smapConfig.getCertificationUrl();
			log.info("=============== sso-web 认证地址：" + requestUrl);

			List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
			requestParams.add(new BasicNameValuePair("userCode", DESUtil.encrypt(param.getUserCode())));
			requestParams.add(new BasicNameValuePair("uidPassword", DESUtil.encrypt(param.getUidPassword())));
			requestParams.add(new BasicNameValuePair("app", DESUtil.encrypt(param.getApp())));
			
			HttpPost httpPost = new HttpPost(requestUrl);
			httpPost.setEntity(new UrlEncodedFormEntity(requestParams, Consts.UTF_8));

			HttpClient client = HttpClients.createDefault();
			HttpResponse httpResponse = client.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			String entityStr = EntityUtils.toString(entity);
			
			ResultJSON resultJSON = JSONObject.parseObject(entityStr, ResultJSON.class);
			return resultJSON;
		} catch (Exception e) {
			log.error("登陆失败", e);
			throw BusinessException.build("SSO_0001", "登录");
		}
	}
	
	/**
	 * 单点注销
	 * 
	 * @param param
	 *            封装操作所需参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public ResultJSON logout(@RequireValid Logout param) throws BusinessException {

		Message m = null;
		ResultJSON rj = new ResultJSON();
		try {
			String key = param.getUserCode() + "-" + param.getApp();
			jedisClusterService.del(key);

			m = PropertiesUtil.getMessage("COMMON_200");
			rj.setReturnCode(m.getCode());
			rj.setReturnTip(m.getTip());
			rj.setBody(new JSONObject());
		} catch (Exception e) {
			log.info("单点注销出错", e);
			throw BusinessException.build("SSO_0001", "注销");
		}
		return rj;
	}	
	
	/**
	 * 票据验证
	 * 
	 * @param param
	 *            封装操作所需参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@RequestMapping("/validate")
	@ResponseBody
	public ResultJSON validate(@RequireValid Login param) throws BusinessException {

		Message m = null;
		ResultJSON rj = new ResultJSON();
		try {
			String key = param.getUserCode() + "-" + param.getApp();
			String ticket = jedisClusterService.get(key);
			
			if(StringUtils.isNotBlank(ticket) && StringUtils.equals(ticket, param.getToken())) {
				// 验证成功，返回用户 uid和票据
				m = PropertiesUtil.getMessage("COMMON_200");
				rj.setReturnCode(m.getCode());
				rj.setReturnTip(m.getTip());
				
				JSONObject body = new JSONObject();
				body.put("userCode", param.getUserCode());
				body.put("token", param.getToken());
				
				rj.setBody(body);				
			} else {
				// 验证失败，返回错误提示码
				m = PropertiesUtil.getMessage("SSO_0002");
				rj.setReturnCode(m.getCode());
				rj.setReturnTip(m.getTip());
				rj.setBody(new JSONObject());
			}
			return rj;
		} catch (Exception e) {
			log.error("验证票据出错", e);
			throw BusinessException.build("SSO_0002");
		}
	}	
}
