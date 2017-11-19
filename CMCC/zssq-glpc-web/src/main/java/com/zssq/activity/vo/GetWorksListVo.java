package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: GetWorksListVo  
 * @Description: 查询作品列表
 * @author liuzhijie  
 * @date 2017年4月24日  
 *
 */
public class GetWorksListVo implements Serializable {

	private static final long serialVersionUID = -255360283232516539L;

	@NotBlank(message = "{empty.message}")
	private String activityCode;//活动CODE
	
	@NotBlank(message = "{empty.message}")
	private String joinCode;//参与记录CODE
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户唯一标识CODE 
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getJoinCode() {
		return joinCode;
	}

	public void setJoinCode(String joinCode) {
		this.joinCode = joinCode;
	}

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
