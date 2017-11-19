package com.zssq.news.newsvo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 
 * @ClassName: NewsCommentListVO
 * @Description: 新闻评论列表查询类
 * @author SharlaCheung
 * @date 2017年4月10日
 *
 */
public class NewsReplyDeleteVO implements Serializable {

	private static final long serialVersionUID = -1606166264275829882L;
	@NotBlank(message = "{empty.message}")
	private String userCode;

	@NotBlank(message = "{empty.message}")
	private String replyCode;

	@NotBlank(message = "{empty.message}")
	private String commentCode;
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getReplyCode() {
		return replyCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
	
	
}
