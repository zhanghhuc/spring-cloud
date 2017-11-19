package com.zssq.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zssq.constants.AppConstants;
import com.zssq.pojo.ResultJSON;
import com.zssq.utils.DESUtil;
import com.zssq.utils.PropertiesUtil;
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
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zssq.pojo.Login;
import com.zssq.pojo.Message;
import com.zssq.pojo.Ticket;

import redis.clients.jedis.Jedis;

/**
 * SSO 单点登录票据验证拦截器
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public class SSOInterceptor implements HandlerInterceptor {

	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());	

	// TODO 修改为 disconf 管理
	/** SSO 单点登录资源配置文件 */
	private Properties props = new PropertiesUtil("sso.properties");
	private Properties propsRedis = new PropertiesUtil("ssoRedis.properties");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 获取 SSOAuthInterceptor 拦截器验证结果，不为空，则表示未通过验证，直接进入后续切面处理
		Object ticketMsgAuth = request.getAttribute("ticketMsgAuth");		
		if (ticketMsgAuth != null) { 
			return true;
		}		
		
		// 获取请求参数		
		// 员工 uid
		String uid = request.getParameter("userCode");
		// 密码
		String uidPassword = request.getParameter("uidPassword");
		// 应用类型【mhpc,mhh5,glpc,saas,admin】
		String app = request.getParameter("app");
		// 票据
		String ticket = request.getParameter("token");
		 // 票据跨域已在前端存放
		String ticketSuccess = request.getParameter("ticketSuccess");
		
		// 确保票据有值，供服务端判断使用
		if (ticket == null || "".equals(ticket)) { 
			ticket = "~";
		}
		
		// 判断是否为注销操作【0:注销, 1:不注销】，是，则调用 sso-web 服务执行注销操作
		String isLogout = request.getParameter("isLogout"); 		
		if ("0".equals(isLogout)) { 
			getHttpClientJSON(uid, "", app, "0", ticket);
			return true;
		}

		boolean result = false;		

		// 连接 redis 缓存服务器
		@SuppressWarnings("resource")
		Jedis redis = new Jedis(propsRedis.getProperty("webRedisIP"), Integer.valueOf(propsRedis.getProperty("webRedisPort"))); 
		redis.auth(propsRedis.getProperty("webRedisPwd"));
		log.info("SSOInterceptor 拦截器，连接 redis 缓存服务器运行状态: " + redis.ping());

		// 以票据为键，从 redis 缓存服务器中获取信息
		String resultRedis = redis.get(ticket);
		log.info("SSOInterceptor 拦截器，从 redis 中获取信息，键 = " + ticket + "，值= " + resultRedis);

		// redis 缓存服务器中可以获取到信息，并且 ticketSuccess=1，说明票据跨域在前端已存放，并且是最新票据，可直接进入其他层
		if (resultRedis != null && "1".equals(ticketSuccess)) { 
			log.info("SSOInterceptor 拦截器，缓存 + ticketSuccess 验证通过");		
			return true;
		}

		if (resultRedis != null) { 
			log.info("SSOInterceptor 拦截器，跨域票据未在前端保存（缓存中获取到了有效票据，但缺失 ticketSuccess 参数）");
			
			ResultJSON resultJSON = new ResultJSON();
			Message m = PropertiesUtil.getMessage("SSO_0003");
			resultJSON.setReturnCode(m.getCode());
			resultJSON.setReturnTip(m.getTip());
			
			String[] values = resultRedis.split("-");
			Login login = new Login();
			login.setUserCode(values[0]);
			login.setApp(values[1]);
			login.setToken(ticket);
			resultJSON.setBody(login);

			// 跨域票据验证失败，在请求对象 request 中设置 crossDomainTicketMsg 属性，后续有 SpringAspect 公用切面做返回处理
			request.setAttribute("crossDomainTicketMsg", JSONObject.toJSONString(resultJSON));
			return true;
		}

		if (AppConstants.SAAS.equals(app) || AppConstants.ADMIN.equals(app)) {
			result = true;
		} else {
			if (resultRedis == null) {
				// redis 缓存中获取不到信息，调用 sso-web 进行身份认证
				String entityStr = getHttpClientJSON(uid, uidPassword, app, "", ticket);
				
				Ticket t = null;
				ResultJSON resultJSON = JSONObject.parseObject(String.valueOf(entityStr), ResultJSON.class);
				if (resultJSON.getReturnCode().equals("200")) { 
					// sso-web 认证成功时，在 redis 缓存中保存票据信息
					t = JSONObject.parseObject(String.valueOf(resultJSON.getBody()), Ticket.class);
					redis.del(t.getToken());
					redis.set(t.getToken(), uid + "-" + app + "-" + uidPassword);
				}

				request.setAttribute("ticketMsg", entityStr);

				if (resultJSON.getReturnCode().equals("0001") || resultJSON.getReturnCode().equals("402")
						|| resultJSON.getReturnCode().equals("0004")) {
					return true;
				}

				// 当定时清空redis后，客户端没有注销，相当于续签，客户端还有票据时，存入客户端旧票据在aop中跟新票据比对后，客户端存入新票据
				if (!t.getToken().equals(ticket) && !"~".equals(ticket)) {
					request.setAttribute("clientTicketMsg", ticket);
				}

				log.info("SSOInterceptor.preHandle:认证成功，存入本地redis令牌," + ticket);
			}

			result = true;
		}

		return result;

	}

	/**
	 * 使用 HttpClient 工具，调用指定的远程 HTTP 接口，并返回 JSON 结果对象
	 * 
	 * @param uid
	 * 			员工 uid
	 * @param uidPassword
	 * 			员工登陆密码
	 * @param app
	 * 			应用类型
	 * @param isLogout
	 * 			是否注销
	 * @param ticket
	 * 			票据
	 * @return 远程 HTTP 接口执行结果
	 * @throws Exception
	 */
	private String getHttpClientJSON(String uid, String uidPassword, String app, String isLogout, String ticket)
			throws Exception {
		
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		String requestUrl = "";
		if (!"".equals(isLogout)) {
			requestUrl = props.getProperty("sso_logout");
		} else {
			requestUrl = props.getProperty("sso_certification");
		}

		HttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(requestUrl);
		if (!"".equals(uid) && uid != null) {
			requestParams.add(new BasicNameValuePair("userCode", DESUtil.encrypt(uid)));
		}
		if (!"".equals(uidPassword) && uidPassword != null) {
			requestParams.add(new BasicNameValuePair("uidPassword", DESUtil.encrypt(uidPassword))); // 需要加密
		}
		if (!"".equals(app) && app != null) {
			requestParams.add(new BasicNameValuePair("app", DESUtil.encrypt(app)));
		}
		/*
		 * if(!"".equals(isClient) && isClient != null){ requestParams.add(new
		 * BasicNameValuePair("isClient", DESUtil.encrypt(isClient)));
		 * //是否是相同客户端下，同一台机器登录操作，0：是，1：否 }
		 */
		if (!"".equals(isLogout) && isLogout != null) {
			requestParams.add(new BasicNameValuePair("isLogout", DESUtil.encrypt(isLogout)));
		}
		if (!"".equals(ticket) && ticket != null) {
			requestParams.add(new BasicNameValuePair("token", DESUtil.encrypt(ticket)));
		}

		httpPost.setEntity(new UrlEncodedFormEntity(requestParams, Consts.UTF_8));

		HttpResponse httpResponse = client.execute(httpPost);

		log.info("Request.httpPost.getURI(): " + httpPost.getURI());
		log.info("Response Code: " + httpResponse.getStatusLine().getStatusCode());
		HttpEntity entity = httpResponse.getEntity();
		String entityStr = EntityUtils.toString(entity);
		log.info("entityStr:" + entityStr);

		return entityStr;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}	
}