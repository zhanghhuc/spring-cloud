package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetPortalFrontQualityListVO
 * @Description 门户展示精华列表
 * @author LXW
 * @date 2017年6月9日 上午10:41:00
 * @version 1.0
 * @since JDK 1.7
 */
public class GetPortalFrontQualityListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String orgCode; // 门户编号
	@IntType(expression = ">0")
	private String showSize; // 显示条数
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户的userCode
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getShowSize() {
		return showSize;
	}

	public void setShowSize(String showSize) {
		this.showSize = showSize;
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