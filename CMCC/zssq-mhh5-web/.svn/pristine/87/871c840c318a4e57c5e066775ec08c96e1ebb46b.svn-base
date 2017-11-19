package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
/**
 * 收藏/取消收藏输入参数对象
 * @ClassName CollectingOrNotVo
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午5:56:07
 * @version 1.0
 * @since JDK 1.7
 */
public class CollectingOrNotVo {

	@NotBlank(message = "{empty.message}")
	private String activityCode; // 活动唯一标识CODE

	@EnumType(required = true, allow = { "0", "1" })
	private String operating; // 操作标识(0-取消收藏 1-收藏)

	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户唯一标识CODE

	@NotBlank(message = "{empty.message}")
	private String token; // Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getOperating() {
		return operating;
	}

	public void setOperating(String operating) {
		this.operating = operating;
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
