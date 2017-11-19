package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
 * @ClassName: GetPortalKnowledgeListByLibVo  
 * @Description: 获取知识库下知识 vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class GetPortalKnowledgeListByLibVo {
	
	private String repositoryCode;//数据库编号
	@LongType(expression = ">0")
	private String queryTime;//查询时间
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户编号
	@NotBlank(message = "{empty.message}")
	private String token;//用户token
	@IntType(expression = ">0")
	private String pageSize;//每页数量
	@IntType(expression = ">=0")
	private String pageNo;//页码
	public String getRepositoryCode() {
		return repositoryCode;
	}
	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
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
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	
	

}
