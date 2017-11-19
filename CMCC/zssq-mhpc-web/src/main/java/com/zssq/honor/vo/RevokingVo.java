package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName RevokingVo
 * @Description 撤销荣誉输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:25:45
 * @version 1.0
 * @since JDK 1.7
 */
public class RevokingVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String awardCode;  //授予记录CODE 
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getAwardCode() {
		return awardCode;
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

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
