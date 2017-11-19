package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: GetPortalKnowledgeAppendVo  
 * @Description: 获取知识追加 vo  
 * @author sry  
 * @date 2017年4月28日  
 *
 */
public class GetPortalKnowledgeAppendVo {
	@NotBlank(message = "{empty.message}")
	private String knowledgeCode;//知识code
	@IntType(expression = ">0")
	private String pageSize;//每页数量
	@IntType(expression = ">=0")
	private String pageNo;//页码
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户编号
	@NotBlank(message = "{empty.message}")
	private String token;//token
	@EnumType(allow={"0","1"},required=false)
	private String isSelfAppend;//查询追加范围 0：他人，1，自己 ,"":全部 
	public String getKnowledgeCode() {
		return knowledgeCode;
	}
	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
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
	public String getIsSelfAppend() {
		return isSelfAppend;
	}
	public void setIsSelfAppend(String isSelfAppend) {
		this.isSelfAppend = isSelfAppend;
	}
	
	
}
