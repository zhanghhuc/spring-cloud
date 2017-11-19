package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;
/**
 * 获取我参与的投票列表输入参数
 * @ClassName GetJoinVoteListVo
 * @Description 
 * @author liurong
 * @date 2017年4月5日 下午2:46:38
 * @version 1.0
 * @since JDK 1.7
 */
public class GetJoinVoteListVo {

	@EnumType(required = false, allow = { "4", "5" })
	private String voteStatus;

	@IntType(required = false, expression = ">0")
	private String id;
	
	@NotBlank(message = "{empty.message}")
	private String joinUserCode;

	@IntType(expression = ">0")
	private String pageSize;

	@NotBlank(message = "{empty.message}")
	private String userCode;

	@NotBlank(message = "{empty.message}")
	private String token;

	public String getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(String voteStatus) {
		this.voteStatus = voteStatus;
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

	public String getJoinUserCode() {
		return joinUserCode;
	}

	public void setJoinUserCode(String joinUserCode) {
		this.joinUserCode = joinUserCode;
	}
}
