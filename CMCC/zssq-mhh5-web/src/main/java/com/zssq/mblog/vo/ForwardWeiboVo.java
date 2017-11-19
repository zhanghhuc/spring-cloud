package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
    * @ClassName: ForwardWeiboVo  
    * @Description: 转发微博条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class ForwardWeiboVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token;  // TOKEN
	@NotBlank(message = "{empty.message}") 
	private String mblogCode; // 被转发原微博CODE
	@NotBlank(message = "{empty.message}") 
	private String mblogUserCode; // 被转发原微博发布人CODE
	@NotBlank(message = "{empty.message}")
	private String content; // 内容
	@NotBlank(message = "{empty.message}")
	private String textContent; // 文本内容
	@NotBlank(message = "{empty.message}")
	private String summary; // 摘要
	@NotBlank(message = "{empty.message}")
	private String forMblogCode; // 被转发微博CODE
	@NotBlank(message = "{empty.message}")
	private String forUserCode; // 被转发微博发布人CODE
	
	private String atUserCodes = ""; // at用户CODE
	
	
	
	
	public String getForMblogCode() {
		return forMblogCode;
	}



	public void setForMblogCode(String forMblogCode) {
		this.forMblogCode = forMblogCode;
	}



	public String getForUserCode() {
		return forUserCode;
	}



	public void setForUserCode(String forUserCode) {
		this.forUserCode = forUserCode;
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



	public String getMblogUserCode() {
		return mblogUserCode;
	}



	public void setMblogUserCode(String mblogUserCode) {
		this.mblogUserCode = mblogUserCode;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getTextContent() {
		return textContent;
	}



	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}



	public String getAtUserCodes() {
		return atUserCodes;
	}



	public void setAtUserCodes(String atUserCodes) {
		this.atUserCodes = atUserCodes;
	}

	
}
