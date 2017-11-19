package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: RepositoryKnowledgeVo  
 * @Description: 知识vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class RepositoryKnowledgeModel implements Serializable{

	private static final long serialVersionUID = 2670843916235809938L;
	
	private String orgCode = "";//组织编号
	
	private String repositoryCode = "";//知识库编号
	
	private Long queryTime = 0L;//查询时间
	
	private String userCode = "";//用户编号
	
	private Integer pageNo = 0;//页码
	
	private Integer pageSize = 0;//每页数量

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
	
	

}
