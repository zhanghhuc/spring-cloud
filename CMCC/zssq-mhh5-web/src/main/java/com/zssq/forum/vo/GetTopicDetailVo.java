package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 查询主帖详情输入参数对象
 * @ClassName GetTopicDetailVo
 * @Description 
 * @author liurong
 * @date 2017年5月26日 下午3:17:32
 * @version 1.0
 * @since JDK 1.7
 */
public class GetTopicDetailVo {
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;
	
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
