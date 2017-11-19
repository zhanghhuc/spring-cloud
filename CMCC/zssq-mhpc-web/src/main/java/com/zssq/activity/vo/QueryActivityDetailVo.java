package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: QueryActivityDetailVo
 * @Description: 查询活动详情
 * @author liuzhijie
 * @date 2017年4月24日
 *
 */
public class QueryActivityDetailVo implements Serializable {

	private static final long serialVersionUID = -7591031807734743579L;

	@NotBlank(message = "{empty.message}")
	private String activityCode;//活动CODE
	
	@EnumType(allow={"1"})
	private String activityType;//活动类型：1-文章征集
	
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

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
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
