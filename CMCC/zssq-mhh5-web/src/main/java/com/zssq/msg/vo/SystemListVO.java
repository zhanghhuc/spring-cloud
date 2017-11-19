package com.zssq.msg.vo;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: SystemListVO  
 * @Description: 系统消息查询Vo  
 * @author YDB  
 * @date 2017年4月5日  
 *
 */
public class SystemListVO {
	
	private String userCode;
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String PageNo;

	
	
	private String title;
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	

	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNo() {
		return PageNo;
	}
	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}