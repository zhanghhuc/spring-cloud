package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.LongType;

/**
 * 
 * @ClassName: GetPortalKnowledgeLibListVo  
 * @Description: 获取公司下知识库 vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class GetPortalKnowledgeLibListVo {
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	@LongType(expression = ">0")
	private String queryTime;
	@NotBlank(message = "{empty.message}")
	private String userCode;
	@NotBlank(message = "{empty.message}")
	private String token;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
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
