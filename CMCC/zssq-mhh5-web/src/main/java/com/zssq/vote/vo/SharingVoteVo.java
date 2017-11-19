package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分享投票输入参数对象
 * @ClassName SharingVoteVo
 * @Description 
 * @author liurong
 * @date 2017年4月20日 下午5:36:57
 * @version 1.0
 * @since JDK 1.7
 */
public class SharingVoteVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;  //待分享投票CODE
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

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
