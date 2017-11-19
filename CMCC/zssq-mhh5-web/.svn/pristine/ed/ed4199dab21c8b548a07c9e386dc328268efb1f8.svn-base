package com.zssq.mblog.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
    * @ClassName: DelWeiboVo  
    * @Description: 删除微博条件VO  
    * @author Mr.B  
    * @date 2017年3月22日  
    *
 */
public class DelWeiboVo {

	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	@NotBlank(message = "{empty.message}")
	private String mblogCode; // 微博CODE
	@NotBlank(message = "{empty.message}")
	private  String dynamicCode; // 动态CODE
	@EnumType(required = true,allow = {"1","2"})
	private String mblogSource; // 微博来源
	
	
	public String getMblogSource() {
		return mblogSource;
	}
	public void setMblogSource(String mblogSource) {
		this.mblogSource = mblogSource;
	}
	public String getDynamicCode() {
		return dynamicCode;
	}
	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
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
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	
	
	
}
