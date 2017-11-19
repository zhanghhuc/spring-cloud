package com.zssq.credit.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: IntegralAccountDetailSearchVo  
 * @Description: 校验前端输入参数--积分明细查询
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralAccountDetailSearchVo {
	
	/** 用户code */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 用户token */
	@NotBlank(message = "{empty.message}")
	private String token;
	
	/** 页码 */
	@IntType(expression = ">=0")
	private String pageNo;
	
	/** 每页显示条数 */
	@IntType(expression = ">=0")
	private String pageSize;
	
	/** 积分账户编号 */
	@NotBlank(message = "{empty.message}")
	private String accountCode;

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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
}
