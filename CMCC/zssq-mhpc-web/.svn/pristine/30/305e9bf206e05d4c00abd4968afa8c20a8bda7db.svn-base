package com.zssq.vote.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName DeletingVo
 * @Description 删除投票/评论/回复
 * @author LiuYunLong
 * @date 2017年3月30日 下午4:01:04
 * @version 1.0
 * @since JDK 1.7
 */
public class DeletingVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String infoCode;  //待删除CODE
	
	@EnumType(allow = { "1", "2", "3" })
	private String infoType;  //待删除信息类型(1-评论  2-回复  3-投票)
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getInfoCode() {
		return infoCode;
	}

	public String getInfoType() {
		return infoType;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
