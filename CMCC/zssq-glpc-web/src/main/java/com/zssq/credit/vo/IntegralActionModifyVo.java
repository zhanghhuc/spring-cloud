package com.zssq.credit.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: IntegralActionModifyVo  
 * @Description: 校验前端输入参数--积分行为修改  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralActionModifyVo {
	
	/** 用户code */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 用户token */
	@NotBlank(message = "{empty.message}")
	private String token;
	
	/** 积分行为编号 */
	@NotBlank(message = "{empty.message}")
	private String actionCode;
	
	/** 经验值 */
	@NotBlank(message = "{empty.message}")
	private String expValue;
	
	/** 积分 */
	@NotBlank(message = "{empty.message}")
	private String integralValue;
	
	/** 金币 */
	@NotBlank(message = "{empty.message}")
	private String coinValue;
	
	/** 周期内奖励次数 */
	@IntType(expression = ">=0")
	private String actionCycleCount;

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

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getExpValue() {
		return expValue;
	}

	public void setExpValue(String expValue) {
		this.expValue = expValue;
	}

	public String getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(String integralValue) {
		this.integralValue = integralValue;
	}

	public String getCoinValue() {
		return coinValue;
	}

	public void setCoinValue(String coinValue) {
		this.coinValue = coinValue;
	}

	public String getActionCycleCount() {
		return actionCycleCount;
	}

	public void setActionCycleCount(String actionCycleCount) {
		this.actionCycleCount = actionCycleCount;
	}
	
}
