package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: AdvanceOfflineTimeVo  
 * @Description: 提前下线活动
 * @author liuzhijie  
 * @date 2017年4月24日  
 *
 */
public class AdvanceOfflineTimeVo implements Serializable {
 
	private static final long serialVersionUID = 5242597069424453514L;

	@NotBlank(message = "{empty.message}")
	private String activityCode;//活动CODE
	
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
