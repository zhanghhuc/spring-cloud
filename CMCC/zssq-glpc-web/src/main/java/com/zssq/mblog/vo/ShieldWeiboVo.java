package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
    * @ClassName: ShieldWeiboVo  
    * @Description: 屏蔽微博  
    * @author Mr.B  
    * @date 2017年4月15日  
    *
 */
public class ShieldWeiboVo {


	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode;		// 微博CODE
	@EnumType(required = true,allow = {"1","0"})
	private String isShield;  // 是否屏蔽
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
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public String getIsShield() {
		return isShield;
	}
	public void setIsShield(String isShield) {
		this.isShield = isShield;
	}
	
	
}
