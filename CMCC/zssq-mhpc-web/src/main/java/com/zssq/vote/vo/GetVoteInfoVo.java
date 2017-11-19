package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 查看投票详情输入参数对象
 * @ClassName GetVoteInfoVo
 * @Description 
 * @author liurong
 * @date 2017年4月5日 下午5:19:39
 * @version 1.0
 * @since JDK 1.7
 */
public class GetVoteInfoVo {

	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getVoteInfoCode() {
		return voteInfoCode;
	}

	public void setVoteInfoCode(String voteInfoCode) {
		this.voteInfoCode = voteInfoCode;
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
