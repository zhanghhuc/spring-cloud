package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: DoPortalKnowledgeShareVo  
 * @Description: 分享  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class DoPortalKnowledgeShareVo {
	@NotBlank(message = "{empty.message}")
	private String knowledgeCode;//知识编号
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户编号
	@NotBlank(message = "{empty.message}")
	private String token;//token

	public String getKnowledgeCode() {
		return knowledgeCode;
	}

	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
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
