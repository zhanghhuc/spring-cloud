package com.zssq.vo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledgeAppendVo  
 * @Description: 知识追加vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class RepositoryKnowledgeAppendVo implements Serializable{

	    
	private static final long serialVersionUID = -6043718303164726424L;
	
	private String knowledgeCode;//知识编号
	
	private String userCode;//用户编号
	
	private Integer pageNo;//页码
	
	private Integer pageSize;//每页数量

	private Byte isSelfAppend;//查询追加范围 0：他人，1，自己 ,null:全部 
	
	private String orgCode;//组织编号
	
	private String tenantCode;//租户编号
	
	private String appendContent;//追加内容
	
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

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Byte getIsSelfAppend() {
		return isSelfAppend;
	}

	public void setIsSelfAppend(Byte isSelfAppend) {
		this.isSelfAppend = isSelfAppend;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getAppendContent() {
		return appendContent;
	}

	public void setAppendContent(String appendContent) {
		this.appendContent = appendContent;
	}
	
	
	
}
