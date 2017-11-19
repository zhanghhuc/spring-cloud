package com.zssq.forum.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName EmpowerVo
 * @Description 设置某一论坛权限输入参数对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午2:59:01
 * @version 1.0
 * @since JDK 1.7
 */
public class EmpowerVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String forumCode;  //论坛CODE
	
	@EnumType(required = true,allow = {"0","1"})
	private String isSend;  //是否允许班组成员发帖
	
	@EnumType(required = true,allow = {"0","1"})
	private String isReply;  //是否允许班组成员评论
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getForumCode() {
		return forumCode;
	}

	public String getIsSend() {
		return isSend;
	}

	public String getIsReply() {
		return isReply;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setForumCode(String forumCode) {
		this.forumCode = forumCode;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
