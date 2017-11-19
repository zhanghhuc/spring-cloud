package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
    * @ClassName: DelWeiboComVo  
    * @Description: 删除微博评论  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class DelWeiboComVo {
	
	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode; // 微博CODE
	@NotBlank(message = "{empty.message}")
	private String commentCode; // 评论CODE
	
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
	
	
}
