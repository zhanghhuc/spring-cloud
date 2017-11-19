package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RepositoryKnowledgeVo  
 * @Description: 知识vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class RepositoryKnowledgeVo implements Serializable{

	private static final long serialVersionUID = 2670843916235809938L;
	
	private String orgCode;//组织编号
	
	private String repositoryCode;//知识库编号
	
	private Long queryTime;//查询时间
	
	private String userCode;//用户编号
	
	private Integer pageNo;//页码
	
	private Integer pageSize;//每页数量
	
	private String knowledgeCode;//知识编号
	
	private String tenantCode;//租户编号

	private String knowledgeTitle;//知识标题

	private String knowledgeDigest;//知识摘要
	
	private String contentInfo;//知识详情
	 
	private Integer showSize;//展示条数
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRepositoryCode() {
		return repositoryCode;
	}

	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
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

	public String getKnowledgeCode() {
		return knowledgeCode;
	}

	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getKnowledgeTitle() {
		return knowledgeTitle;
	}

	public void setKnowledgeTitle(String knowledgeTitle) {
		this.knowledgeTitle = knowledgeTitle;
	}

	public String getKnowledgeDigest() {
		return knowledgeDigest;
	}

	public void setKnowledgeDigest(String knowledgeDigest) {
		this.knowledgeDigest = knowledgeDigest;
	}

	public String getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}

	public Integer getShowSize() {
		return showSize;
	}

	public void setShowSize(Integer showSize) {
		this.showSize = showSize;
	}
	
	

}
