package com.zssq.credit.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: IntegralAccountSearchVo  
 * @Description: 校验前端输入参数--积分账户查询  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralAccountSearchVo {

	/** 用户code */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 用户token */
	@NotBlank(message = "{empty.message}")
	private String token;
	
	/** 模糊查询名称 */
	private String name;
	
	/** 所属行政组织编号 */
	private String orgCode;
	
	/** 积分账户编号 */
	private String accountCode;
	
	/** 账户类型：1-个人，2-班组，3-公司 */
	@EnumType(allow = {"1","2","3"})
	private String accountType;
	
	/** 页码 */
	@IntType(expression = ">=0")
	private String pageNo;
	
	/** 每页显示条数 */
	@IntType(expression = ">=0")
	private String pageSize;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
	
}
