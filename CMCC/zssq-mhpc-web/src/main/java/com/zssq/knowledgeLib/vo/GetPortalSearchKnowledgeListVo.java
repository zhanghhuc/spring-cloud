package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
 * @ClassName: GetPortalSearchKnowledgeListVo  
 * @Description: 搜索知识  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class GetPortalSearchKnowledgeListVo {
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	@LongType(expression = ">0")
	private String queryTime;//查询时间
	@IntType(expression = ">0")
	private String pageSize;//每页数量
	@IntType(expression = ">=0")
	private String pageNo;//页码
	@NotBlank(message = "{empty.message}")
	private String userCode;//用code
	@NotBlank(message = "{empty.message}")
	private String token;//token
	private String Keywords;//搜索关键字
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
	public String getKeywords() {
		return Keywords;
	}
	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

	
}
