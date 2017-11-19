package com.zssq.vo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryCollectVo  
 * @Description: 知识库收藏vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class RepositoryCollectVo implements Serializable{

	private static final long serialVersionUID = 839752172447575796L;
	
	private String knowledgeCode;//知识编号
	
	private String userCode;//用户编号

	private String orgCode;//组织编号
	
	private String tenantCode;//租户编号
	
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
	
	
}
