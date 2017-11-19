package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName SettingPrizeForWorksVo
 * @Description 为获奖作品设定奖项输入对象
 * @author LiuYunLong
 * @date 2017年4月18日 上午10:55:05
 * @version 1.0
 * @since JDK 1.7
 */
public class SettingPrizeForWorksVo implements Serializable {

	private static final long serialVersionUID = 1517464830472794726L;
	
	@NotBlank(message = "{empty.message}")
	private String activityCode;  //活动CODE
	
	@NotBlank(message = "{empty.message}")
	private String joinCode;  //活动参与CODE
	
	@NotBlank(message = "{empty.message}")
	private String joinUserCode;  //参与用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String prizeCode;  //奖项CODE
	
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

	public String getJoinUserCode() {
		return joinUserCode;
	}

	public String getPrizeCode() {
		return prizeCode;
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

	public void setJoinUserCode(String joinUserCode) {
		this.joinUserCode = joinUserCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
