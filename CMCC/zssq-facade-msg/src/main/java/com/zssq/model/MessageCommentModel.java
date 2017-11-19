package com.zssq.model;

import java.io.Serializable;

public class MessageCommentModel implements Serializable {

	//用户code
	private String userCode;
	//查询类型 0:收到的评论,1:发出的评论
	private String type;
	
	private int pageSize;
	private int pageNo;
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	
	
	
	
	
}
