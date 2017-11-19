package com.zssq.mblog.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
    * @ClassName: CommentWeiboVo  
    * @Description: 评论微博条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class CommentWeiboVo {
	
	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode; // 微博CODE
	@NotBlank(message = "{empty.message}")
	private String content; // 内容
	
	private List<String> atUserCodes; // AT用户列表


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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getAtUserCodes() {
		return atUserCodes;
	}

	public void setAtUserCodes(List<String> atUserCodes) {
		this.atUserCodes = atUserCodes;
	}
	
	
	
}
