package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName SettingWinForWorksVo
 * @Description 设为/撤销获奖作品输入对象
 * @author LiuYunLong
 * @date 2017年4月18日 上午10:46:19
 * @version 1.0
 * @since JDK 1.7
 */
public class SettingWinForWorksVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String activityCode;  //活动CODE
	
	@NotBlank(message = "{empty.message}")
	private String joinCode;  //活动参与CODE
	
	@EnumType(required = true,allow = {"1","2"})
	private String operateType;  //操作类型
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //当前登录用户唯一标识CODE 
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public String getJoinCode() {
		return joinCode;
	}

	public String getOperateType() {
		return operateType;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public void setJoinCode(String joinCode) {
		this.joinCode = joinCode;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
