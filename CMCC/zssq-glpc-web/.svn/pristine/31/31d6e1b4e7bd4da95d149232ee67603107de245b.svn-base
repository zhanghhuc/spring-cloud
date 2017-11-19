package com.zssq.knowledgeLib.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

public class GetSearchKnowledgeListForGLVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;		//组织编号
	
	@LongType(expression = ">0")
	private Long queryTime;		//首次查询时间，前端生成传入
	
	@IntType(expression = ">0")
	private Integer pageSize;		//每页条数
	
	@IntType(expression = ">=0")
	private Integer pageNo;		//页码，从0开始
	
	@NotBlank(message = "{empty.message}")
	private String userCode;		//当前登录用户的userCode
	
	@NotBlank(message = "{empty.message}")
	private String token;		//token
	
	
	@NotBlank(message = "{empty.message}")
	private String keyWords;		//搜索内容
	

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
}