package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
/**
 * 收藏or取消收藏主帖输入参数对象
 * @ClassName OpearteCollectionVo
 * @Description 
 * @author liurong
 * @date 2017年5月26日 下午3:40:14
 * @version 1.0
 * @since JDK 1.7
 */
public class OpearteCollectionVo {

	@NotBlank(message = "{empty.message}")
	private String topicCode;
	
	@EnumType(allow = { "0", "1" })
	private String operating;//操作动作：0-取消收藏;1-收藏
	
	@NotBlank(message = "{empty.message}")
	private String belongCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getOperating() {
		return operating;
	}

	public void setOperating(String operating) {
		this.operating = operating;
	}

	public String getBelongCode() {
		return belongCode;
	}

	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
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
