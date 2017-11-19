package com.zssq.credit.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: UserLevelConfigSerachVo  
 * @Description: 校验前端输入参数--经验值、等级等信息查询  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class UserLevelConfigSerachVo {

	/** 用户code */
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	/** 用户token */
	@NotBlank(message = "{empty.message}")
	private String token;

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
	
}
