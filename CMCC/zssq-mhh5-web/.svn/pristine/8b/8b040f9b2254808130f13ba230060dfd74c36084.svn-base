package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
    * @ClassName: CollectWeiboVo  
    * @Description: 收藏微博条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class CollectWeiboVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode; // 微博CODE
	@EnumType(required = true,allow = {"1","2"})
	private String actionType; // 1：收藏，2：取消收藏
	
	
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
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	
}
