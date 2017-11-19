package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 发表主帖输入参数对象
 * @ClassName SendTopicVo
 * @Description 
 * @author liurong
 * @date 2017年5月27日 上午10:42:52
 * @version 1.0
 * @since JDK 1.7
 */
public class SendTopicVo {
	
	@NotBlank(message = "{empty.message}")
	private String belongCode;// 论坛所属CODE（这里传入班组CODE即可）

	@NotBlank(message = "{empty.message}")
	private String subject;// 主帖标题

	@NotBlank(message = "{empty.message}")
	private String content;// 主帖内容

	@NotBlank(message = "{empty.message}")
	private String userCode;// 用户CODE

	@NotBlank(message = "{empty.message}")
	private String token;// Token令牌

	public String getBelongCode() {
		return belongCode;
	}

	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
