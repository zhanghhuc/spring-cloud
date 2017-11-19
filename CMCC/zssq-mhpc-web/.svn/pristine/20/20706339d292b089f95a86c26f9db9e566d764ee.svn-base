package com.zssq.credit.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: IntegralActionSearchVo  
 * @Description: 校验前端输入参数：积分行为列表查询  
 * @author power  
 * @date 2017年4月28日  
 *
 */
public class IntegralActionSearchVo {
	
	/** 用户code */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 用户token */
	@NotBlank(message = "{empty.message}")
	private String token;
	
	/** 积分行为类型：1-个人，2-班组，3-公司 */
	@EnumType(allow = {"1","2","3"})
	private String actionType;
	
	/** 分数变化类型：0-所有，1-加分，2-减分 */
	@EnumType(allow = {"0","1","2"})
	private String valueType;
	
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

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	
}
