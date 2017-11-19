package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
/**
 * @ClassName CollectingOrNotVo
 * @Description 收藏/取消收藏传入对象
 * @author LiuYunLong
 * @date 2017年3月30日 下午2:08:59
 * @version 1.0
 * @since JDK 1.7
 */
public class CollectingOrNotVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;  //投票唯一标识CODE
	
	@EnumType(required = true,allow = {"0","1"})
	private String operating;  //操作标识
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getVoteInfoCode() {
		return voteInfoCode;
	}

	public String getOperating() {
		return operating;
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

	public void setOperating(String operating) {
		this.operating = operating;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
