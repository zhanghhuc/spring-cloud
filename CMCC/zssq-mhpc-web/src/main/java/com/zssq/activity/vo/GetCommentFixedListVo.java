package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetCommentFixedList
 * @Description 查询活动评论列表（定位）输入对象
 * @author LiuYunLong
 * @date 2017年4月18日 上午11:03:49
 * @version 1.0
 * @since JDK 1.7
 */
public class GetCommentFixedListVo implements Serializable {

	private static final long serialVersionUID = -1236331813537239199L;
	
	@NotBlank(message = "{empty.message}")
	private String activityCode;  //活动CODE
	
	@NotBlank(message = "{empty.message}")
	private String commentCode;  //需要定位的评论CODE
	
	@IntType(required = false,expression = ">0")
	private String id;  //数据ID
	
	@IntType(required = true,expression = ">0")
	private String pageSize;  //每页显示行数
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getActivityCode() {
		return activityCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public String getId() {
		return id;
	}

	public String getPageSize() {
		return pageSize;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
