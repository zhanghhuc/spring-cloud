package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName DeletingOwnSpeechVo
 * @Description 删除回复/评论输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:14:42
 * @version 1.0
 * @since JDK 1.7
 */
public class DeletingOwnSpeechVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String infoCode;  //信息CODE
	
	@EnumType(required = false,allow = {"1","2"})
	private String infoType;  //信息类型
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE

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
