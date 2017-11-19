package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: MessageSystemModel  
 * @Description: 系统消息查看 model 
 * @author YDB  
 * @date 2017年4月5日  
 *
 */
public class MessageSystemModel implements Serializable {

	//用户code
	private String userCode;
	//标题
	private String title;
	
	private int pageSize;
	
	private int pageNo;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
