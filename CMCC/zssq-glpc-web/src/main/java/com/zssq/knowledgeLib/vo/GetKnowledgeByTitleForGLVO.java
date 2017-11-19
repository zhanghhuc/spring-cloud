package com.zssq.knowledgeLib.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.LongType;

public class GetKnowledgeByTitleForGLVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;		//组织编号
	
	@LongType(expression = ">0")
	private Long queryTime;		//首次查询时间，前端生成传入
	
	@NotBlank(message = "{empty.message}")
	private String userCode;		//当前登录用户的userCode
	
	@NotBlank(message = "{empty.message}")
	private String token;		//token
	
	
	@NotBlank(message = "{empty.message}")
	private String knowledgeCode;		//知识标题


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public Long getQueryTime() {
		return queryTime;
	}


	public void setQueryTime(Long queryTime) {
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




	public String getKnowledgeCode() {
		return knowledgeCode;
	}


	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	
}