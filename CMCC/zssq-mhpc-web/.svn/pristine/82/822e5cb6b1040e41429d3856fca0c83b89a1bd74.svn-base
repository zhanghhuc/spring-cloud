package com.zssq.credit.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: IntegralRankSearchVo  
 * @Description: 校验前端输入参数：积分排行榜列表查询  
 * @author power  
 * @date 2017年4月28日  
 *
 */
public class IntegralRankSearchVo {
	
	/** 用户编号 */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** token */
	@NotBlank(message = "{empty.message}")
	private String token;
	
	/** 排行榜类型：1：个人排行榜；2：班组排行榜 */
	@EnumType(allow = {"1","2"})
	private String accountType;
	
	/** 门户类型：A-集团门户，B-省门户，C-市门户 */
	@EnumType(allow = {"A","B","C"})
	private String mhType;
	
	/** 行政组织编号；示例：查询河北省排行榜，该字段传入河北省组织编码 */
	private String orgCode;
	
	/** 页码  */
	@IntType(expression = ">=0")
	private String pageNo;
	
	/** 每页显示条数  */
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getMhType() {
		return mhType;
	}

	public void setMhType(String mhType) {
		this.mhType = mhType;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
