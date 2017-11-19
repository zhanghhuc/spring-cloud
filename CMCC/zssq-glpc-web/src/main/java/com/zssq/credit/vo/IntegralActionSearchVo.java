package com.zssq.credit.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: IntegralActionSearchVo  
 * @Description: 校验前端输入参数--积分行为查询  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralActionSearchVo {
	
	/** 用户code */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 用户token */
	@NotBlank(message = "{empty.message}")
	private String token;
	
	/** 用户积分行为类型：1-个人，2-班组，3-公司 */
	@EnumType(allow = {"1","2","3"})
	private String actionType;
	
	/** 页码 */
	@IntType(expression = ">=0")
	private String pageNo;
	
	/** 用户每页显示条数 */
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

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
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
