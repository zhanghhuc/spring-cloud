package com.zssq.honor.vo;

import java.io.Serializable;

/**
 * @ClassName GetHonorInfoVo
 * @Description 获取荣誉详情
 * @author LiuYunLong
 * @date 2017年5月4日 上午9:51:44
 * @version 1.0
 * @since JDK 1.7
 */
public class GetHonorInfoVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String honorCode;
	
	private String userCode;
	
	private String token;

	public String getHonorCode() {
		return honorCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setHonorCode(String honorCode) {
		this.honorCode = honorCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
