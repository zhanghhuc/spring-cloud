package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetCommentReplyListForLocationVo
 * @Description 查询评论回复列表 定位输入对象
 * @author LiuYunLong
 * @date 2017年3月22日 下午4:50:44
 * @version 1.0
 * @since JDK 1.7
 */
public class GetCommentReplyListForLocationVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String awardCode;  //荣誉授予记录CODE
	
	@NotBlank(message = "{empty.message}")
	private String commentCode;  //评论CODE
	
	@NotBlank(message = "{empty.message}")
	private String replyCode;  //需要定位的回复CODE
	
	@IntType(required = false,expression = ">0")
	private String id;  //数据ID
	
	@IntType(expression = ">0")
	private String pageSize;  //每页显示行数
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getAwardCode() {
		return awardCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public String getReplyCode() {
		return replyCode;
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

	public void setAwardCode(String awardCode) {
		this.awardCode = awardCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public void setReplyCode(String replyCode) {
		this.replyCode = replyCode;
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
