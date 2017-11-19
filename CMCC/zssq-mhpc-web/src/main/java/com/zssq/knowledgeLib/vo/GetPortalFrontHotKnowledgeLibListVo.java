package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
 * @ClassName: GetPortalFrontHotKnowledgeLibListVo  
 * @Description: 查询集团首页知识库热区  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class GetPortalFrontHotKnowledgeLibListVo {
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;
	@LongType(expression = ">0")
	private String queryTime;
	@IntType(expression = ">0")
	private String showSize;
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
	public String getShowSize() {
		return showSize;
	}
	public void setShowSize(String showSize) {
		this.showSize = showSize;
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
