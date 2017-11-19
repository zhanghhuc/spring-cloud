
package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发表评论输入参数对象
 * @ClassName CommentingVo
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午3:51:58
 * @version 1.0
 * @since JDK 1.7
 */
public class CommentingVo {

	@NotBlank(message = "{empty.message}")
	private String activityCode; // 活动信息唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String content; // 评论内容
	
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
