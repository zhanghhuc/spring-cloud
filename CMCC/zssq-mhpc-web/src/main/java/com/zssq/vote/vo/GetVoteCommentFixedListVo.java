package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetVoteCommentFixedListVo
 * @Description 获取投票评论列表 传入对象 定位
 * @author LiuYunLong
 * @date 2017年4月5日 下午2:56:00
 * @version 1.0
 * @since JDK 1.7
 */
public class GetVoteCommentFixedListVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;  //投票主表信息CODE
	
	@NotBlank(message = "{empty.message}")
	private String commentCode;  //需要定位的评论CODE
	
	private String id;  //数据ID
	
	@IntType(expression = ">0")
	private String pageSize;  //每页显示行数
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getVoteInfoCode() {
		return voteInfoCode;
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

	public void setVoteInfoCode(String voteInfoCode) {
		this.voteInfoCode = voteInfoCode;
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