package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
/**
 * 查询跟帖的评论及回复列表输入参数对象
 * @ClassName GetTopicFollowReplyListVo
 * @Description 
 * @author liurong
 * @date 2017年5月26日 下午6:46:13
 * @version 1.0
 * @since JDK 1.7
 */
public class GetTopicFollowReplyListVo {
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;// 主帖CODE

	@NotBlank(message = "{empty.message}")
	private String followTopicCode;// 跟帖CODE

	@IntType(expression = ">0")
	private String pageSize;// 每页显示行数

	@IntType(required = false, expression = ">0")
	private String id;// 最后一条评论及回复数据的ID，第一次查询传入""

	@NotBlank(message = "{empty.message}")
	private String userCode;// 用户CODE

	@NotBlank(message = "{empty.message}")
	private String token;// Token令牌

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getFollowTopicCode() {
		return followTopicCode;
	}

	public void setFollowTopicCode(String followTopicCode) {
		this.followTopicCode = followTopicCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
