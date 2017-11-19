package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class BoardListVO {
	
	@NotBlank(message = "{empty.message}")
	private String otherUserCode;
	
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String PageNo;

	//查询类型 ：1我收到的留言，2我发出的留言
	@IntType(expression = ">0")
	
	private String type; 
	
	
	public String getOtherUserCode() {
		return otherUserCode;
	}
	public void setOtherUserCode(String otherUserCode) {
		this.otherUserCode = otherUserCode;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}


