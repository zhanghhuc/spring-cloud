package com.zssq.msg.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

public class AtMyListVO implements Serializable {

    
/**  
 * @Fields serialVersionUID : TODO
 */  
private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String PageNo;


	public String getUserCode() {
		return userCode;
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


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	

}