package com.zssq.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

@Service("smapConfig")
@Scope("singleton")
@DisconfFile(filename = "smap.properties")
public class SmapConfig {

	/** 接入应用id */
	private String appId;
	
	/** 接入应用密码 */
	private String appPass;
	
	/** Smap同步员工与组织url */
	private String syncUrl;
	
	/** Smap登录认证url */
	private String authenticateUrl;

	@DisconfFileItem(name = "appId", associateField = "appId")	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@DisconfFileItem(name = "appPass", associateField = "appPass")
	public String getAppPass() {
		return appPass;
	}

	public void setAppPass(String appPass) {
		this.appPass = appPass;
	}

	@DisconfFileItem(name = "syncUrl", associateField = "syncUrl")
	public String getSyncUrl() {
		return syncUrl;
	}

	public void setSyncUrl(String syncUrl) {
		this.syncUrl = syncUrl;
	}

	@DisconfFileItem(name = "authenticateUrl", associateField = "authenticateUrl")
	public String getAuthenticateUrl() {
		return authenticateUrl;
	}

	public void setAuthenticateUrl(String authenticateUrl) {
		this.authenticateUrl = authenticateUrl;
	}
}
