package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: MessagePrivateLetterModel  
 * @Description: 查看私信主列表model  
 * @author YDB  
 * @date 2017年3月29日  
 *
 */
public class MessagePrivateLetterModel implements Serializable{
	
	//用户code
	private String userCode;
	
	
	private Integer pageSize;
	
	private Integer pageNo;

	
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	
}