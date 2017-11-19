package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: GetDynamicSourceVO
 * @Description: 查询动态来源参数
 * @author ZKZ
 * @date 2017年4月11日
 *
 */
public class GetDynamicSourceVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String dynamicCode; // 动态编号
	@NotBlank(message = "{empty.message}")
	private String dynamicUserCode; // 动态所属人编号
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	public String getDynamicCode() {
		return dynamicCode;
	}

	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}

	public String getDynamicUserCode() {
		return dynamicUserCode;
	}

	public void setDynamicUserCode(String dynamicUserCode) {
		this.dynamicUserCode = dynamicUserCode;
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
