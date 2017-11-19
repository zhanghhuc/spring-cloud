package com.zssq.activity.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;
/**
 * 查询活动评论列表输入参数对象
 * @ClassName GetActivityCommentListVo
 * @Description 
 * @author liurong
 * @date 2017年4月21日 下午2:52:45
 * @version 1.0
 * @since JDK 1.7
 */
public class GetActivityCommentListVo {
	
	@NotBlank(message = "{empty.message}")
	private String activityCode; // 活动信息唯一标识CODE

	@IntType(required = false, expression = ">0")
	private String id; // 评论数据ID

	@IntType(required = true, expression = ">0")
	private String pageSize; // 每页显示行数

	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户唯一标识CODE

	@NotBlank(message = "{empty.message}")
	private String token; // Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
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
