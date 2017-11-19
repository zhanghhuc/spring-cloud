package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetReplyListVo
 * @Description 获取回复列表输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:23:27
 * @version 1.0
 * @since JDK 1.7
 */
public class GetReplyListVo implements Serializable{

	private static final long serialVersionUID = 2970143790726160575L;
	
	@NotBlank(message = "{empty.message}")
	private String awardCode;  //荣誉授予记录CODE
	
	@NotBlank(message = "{empty.message}")
	private String commentCode;  //评论CODE
	
	@IntType(required = false,expression = ">0")
	private String id;  //数据ID
	
	@IntType(expression = ">0")
	private String pageSize;  //每页显示行数
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE

	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAwardCode() {
		return awardCode;
	}

	public String getCommentCode() {
		return commentCode;
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

	public void setAwardCode(String awardCode) {
		this.awardCode = awardCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
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
