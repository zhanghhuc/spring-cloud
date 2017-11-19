package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName AdvanceFinishVoteVo
 * @Description 提前结束投票传入对象
 * @author LiuYunLong
 * @date 2017年3月30日 下午3:57:44
 * @version 1.0
 * @since JDK 1.7
 */
public class AdvanceFinishVoteVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;  //投票信息唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getVoteInfoCode() {
		return voteInfoCode;
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

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
