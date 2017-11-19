package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
    * @ClassName: GetWeiboInfoVo  
    * @Description: 获取微博详情条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class GetWeiboInfoVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode; // 微博CODE
	
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
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	
	
}
