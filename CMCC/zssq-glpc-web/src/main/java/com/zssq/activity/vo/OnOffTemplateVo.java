package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class OnOffTemplateVo {

	@NotBlank(message = "{empty.message}")
	private String templateCode;
	
	@EnumType(allow={"0","1"})
	private String sellStatus;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
