package com.zssq.service.impl;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.common.cache.client.JedisClusterService;
import com.zssq.config.SmapConfig;
import com.zssq.dao.mapper.SSOMapper;
import com.zssq.dao.pojo.SSO;
import com.zssq.exceptions.BusinessException;
import com.zssq.service.ISSOService;
import com.zssq.utils.DateUtils;

import com.siemens.ct.its.smap2.client.XSmap2Client;
import com.siemens.ct.its.smap2.client.XSmap2ClientFactory;
import com.siemens.ct.its.smap2.client.XSmapClientLogger;

@Service("ssoService")
public class SSOServiceImpl implements ISSOService {

	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** sso 表持久化操作 */
	@Autowired
	private SSOMapper ssoMapper;
	
	/** redis 操作组件 */
	@Autowired
	private JedisClusterService jedisClusterService;
	
	/** Smap配置属性 */
	@Autowired
	private SmapConfig smapConfig;

	@Override
	public String certification(String uid, String uidPassword, String app) throws BusinessException {
		
		log.info("disconf 配置属性：" + smapConfig.getAppId() + ", " + smapConfig.getAppPass());
		
		// 调用 smap 接口认证，认证通过后发放新票据
//		boolean smapResult = smapAuthentification(uid, uidPassword,"static");
		boolean smapResult = true;
		
		if(smapResult) {
			String ticket = getTicket(uid, app);
			
			// redis 中缓存新票据，时效为3个小时
			String key = uid + "-" + app;
			jedisClusterService.set(key, ticket, 3*60*60);
			
			// sso表中保存票据发放记录，用于记录用户登录轨迹（只记录登录成功的）
			SSO sso = new SSO();
			sso.setUid(uid);
			sso.setApp(app);
			sso.setTicket(ticket);
			sso.setLoginTime(DateUtils.getTime());
			sso.setIsCertification(0);
			sso.setIsRenew(0);
			sso.setIsOver(0); 
			sso.setCreateTime(DateUtils.getTime());
			ssoMapper.insertSelective(sso);	
			
			return ticket;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取票据
	 * 
	 * @param uid 
	 * 			员工账户
	 * @param app 
	 * 			应用类型
	 * @return 票据
	 */
	public String getTicket(String uid, String app){
		
		StringBuffer ticket = new StringBuffer();
		
		ticket.append(String.valueOf(UUID.randomUUID()).replace("-", ""));	
		ticket.append(uid);
		ticket.append(app);
		ticket.append(DateUtils.getTime());
			
		return ticket.toString();
	}
	
	/**
	 * 移动认证
	 * 
	 * @param uid 
	 * 			员工uid
	 * @param uidPassword 
	 * 			用户密码
	 * @param authType 
	 * 			认证类型：目前为静态认证
	 * @return
	 */
	public boolean smapAuthentification(String uid, String uidPassword, String authType){
		
		int result = -1;
		XSmapClientLogger.isDebug = true;
		XSmap2ClientFactory.setLog4jClientLogger("smapClientLogger");
		
		XSmap2Client client = XSmap2ClientFactory.getInstance(smapConfig.getAppId(), smapConfig.getAuthenticateUrl());
		if (client != null) {
			if (authType.equalsIgnoreCase("static")) //静态认证
				result = client.authenticateUserByPassword(uid, uidPassword);
		}
		
		if(result != 0){
			return false;
		}		
		return true;
	}
}
