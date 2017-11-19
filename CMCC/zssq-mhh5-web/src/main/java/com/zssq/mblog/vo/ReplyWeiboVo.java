package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
    * @ClassName: ReplyWeiboVo  
    * @Description: 回复微博条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class ReplyWeiboVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode; // 微博CODE
	@NotBlank(message = "{empty.message}")
	private String commentCode; // 评论CODE
	@NotBlank(message = "{empty.message}")
	private String content; // 内容
	private String replyedReplyCode; // 被回复回复CODE
	private String atUserCodes = ""; // AT人员列表

	
	
	public String getReplyedReplyCode() {
		return replyedReplyCode;
	}

	public void setReplyedReplyCode(String replyedReplyCode) {
		this.replyedReplyCode = replyedReplyCode;
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

	public String getMblogCode() {
		return mblogCode;
	}

	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAtUserCodes() {
		return atUserCodes;
	}

	public void setAtUserCodes(String atUserCodes) {
		this.atUserCodes = atUserCodes;
	}
	
}
