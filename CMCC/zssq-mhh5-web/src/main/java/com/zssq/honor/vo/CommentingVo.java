package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName CommentingVo
 * @Description 发表评论输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:14:01
 * @version 1.0
 * @since JDK 1.7
 */
public class CommentingVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String awardCode;  //荣誉授予记录CODE
	
	@NotBlank(message = "{empty.message}")
	private String content;  //评论内容
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE

	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getAwardCode() {
		return awardCode;
	}

	public void setAwardCode(String awardCode) {
		this.awardCode = awardCode;
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
