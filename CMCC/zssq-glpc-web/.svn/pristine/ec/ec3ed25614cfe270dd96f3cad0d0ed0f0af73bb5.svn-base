package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: DoScoringForWorksVo  
 * @Description: 给作品评分 
 * @author liuzhijie  
 * @date 2017年4月24日  
 *
 */
public class DoScoringForWorksVo implements Serializable {
 
	private static final long serialVersionUID = -6058280631802520646L;

	@NotBlank(message = "{empty.message}")
	private String activityCode;//活动CODE
	
	@NotBlank(message = "{empty.message}")
	private String joinCode;//活动参与CODE
	
	@IntType(expression = ">=0")
	@Range(min=0,max=100)
	private String score;//分数，整数，范围如下：0=<score<=100
	
	private String reason;//评分理由
	
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
