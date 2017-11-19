package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: MsgSysNoticeListVO  
 * @Description: 系统通知列表  
 * @author YDB  
 * @date 2017年5月2日  
 *
 */

public class MsgSysNoticeListVO {
	
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String PageNo;

	public String getUsersCode() {
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

	public String getUserCode() {
		return userCode;
	}

	
	

}
