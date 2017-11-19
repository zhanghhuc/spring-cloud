package com.zssq.vo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledgeAppendVo  
 * @Description: 知识追加vo  
 * @author guoyang 
 * @date 2017年5月2日  
 *
 */
public class GetKnowledgeAppendForGLVo implements Serializable{

	    
	private static final long serialVersionUID = -6043718303164726424L;
	
	private String knowledgeCode;//知识编号
	
	private String userCode;//用户编号
	
	private Integer pageNo;//页码
	
	private Integer pageSize;//每页数量
	
	private Integer isSelfAppend;

	public Integer getIsSelfAppend() {
		return isSelfAppend;
	}

	public void setIsSelfAppend(Integer isSelfAppend) {
		this.isSelfAppend = isSelfAppend;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
	
	
}
