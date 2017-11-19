package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发表回复输入参数对象
 * @ClassName ReplyingVo
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午4:59:13
 * @version 1.0
 * @since JDK 1.7
 */
public class ReplyingVo {

	@NotBlank(message = "{empty.message}")
	private String activityCode;  // 活动信息唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String commentCode;  // 评论CODE
	
	@NotBlank(message = "{empty.message}")
	private String questionerCode;  // 被回复人CODE
	
	@NotBlank(message = "{empty.message}")
	private String content;  // 评论内容
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  // 当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  // Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public String getQuestionerCode() {
		return questionerCode;
	}

	public void setQuestionerCode(String questionerCode) {
		this.questionerCode = questionerCode;
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
