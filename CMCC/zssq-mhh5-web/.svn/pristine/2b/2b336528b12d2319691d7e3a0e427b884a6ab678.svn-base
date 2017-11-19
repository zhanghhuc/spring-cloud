package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName ReplyingVo
 * @Description 发表回复输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:24:38
 * @version 1.0
 * @since JDK 1.7
 */
public class ReplyingVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String awardCode; //荣誉授予记录CODE
	
	@NotBlank(message = "{empty.message}")
	private String commentCode; //评论CODE
	
	@NotBlank(message = "{empty.message}")
	private String questionerCode; //被回复人CODE
	
	@NotBlank(message = "{empty.message}")
	private String content; //回复内容
	
	@NotBlank(message = "{empty.message}")
	private String userCode; //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token; //Token令牌

	public String getAwardCode() {
		return awardCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public String getQuestionerCode() {
		return questionerCode;
	}

	public String getContent() {
		return content;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setAwardCode(String awardCode) {
		this.awardCode = awardCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public void setQuestionerCode(String questionerCode) {
		this.questionerCode = questionerCode;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
