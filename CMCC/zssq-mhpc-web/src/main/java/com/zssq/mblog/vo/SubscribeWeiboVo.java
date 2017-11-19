package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
    * @ClassName: SubscribeWeiboVo  
    * @Description: 订阅微博条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class SubscribeWeiboVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@EnumType(required = true,allow = {"1","2"})
	private String actionType; // 1：订阅，2：取消订阅
	@EnumType(required = true,allow = {"1","2"})
	private String subType; // 1：个人，2：班组
	
	private String subUserCode; // 被订阅的个人CODE
	private String subTeamCode; // 被订阅的班组CODE
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
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getSubUserCode() {
		return subUserCode;
	}
	public void setSubUserCode(String subUserCode) {
		this.subUserCode = subUserCode;
	}
	public String getSubTeamCode() {
		return subTeamCode;
	}
	public void setSubTeamCode(String subTeamCode) {
		this.subTeamCode = subTeamCode;
	}
	
}
