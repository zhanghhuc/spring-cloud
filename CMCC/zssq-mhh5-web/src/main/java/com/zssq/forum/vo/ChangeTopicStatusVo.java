package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
/**
 * 修改主帖状态输入参数对象
 * @ClassName ChangeTopicStatusVo
 * @Description 
 * @author liurong
 * @date 2017年5月26日 下午3:26:21
 * @version 1.0
 * @since JDK 1.7
 */
public class ChangeTopicStatusVo {
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;
	
	@EnumType(allow = { "top", "cancelTop", "best", "cancelBest", "delete" })
	private String operating;
	
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
