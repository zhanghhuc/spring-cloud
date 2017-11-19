package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetVoteCommentListVo
 * @Description 获取评论列表传入对象
 * @author LiuYunLong
 * @date 2017年3月30日 下午2:27:27
 * @version 1.0
 * @since JDK 1.7
 */
public class GetVoteCommentListVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;  //投票信息唯一标识CODE
	
	@IntType(required = false, expression = ">0")
	private String id;  //评论数据ID
	
	@IntType(required = true, expression = ">0")
	private String pageSize;  //每页显示行数
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getVoteInfoCode() {
		return voteInfoCode;
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


	public void setVoteInfoCode(String voteInfoCode) {
		this.voteInfoCode = voteInfoCode;
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
