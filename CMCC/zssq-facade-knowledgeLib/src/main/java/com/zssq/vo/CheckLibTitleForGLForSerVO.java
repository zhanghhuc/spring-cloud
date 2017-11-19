package com.zssq.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 
 * @ClassName: CheckLibTitleForGLForSerVO  
 * @Description: 检查数据标题  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class CheckLibTitleForGLForSerVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;		//组织编号
	
	@NotBlank(message = "{empty.message}")
	private String repositoryTitle;		//知识库标题（后台需验重）
	
	
	@NotBlank(message = "{empty.message}")
	private String userCode;		//当前登录用户的userCode
	
	@NotBlank(message = "{empty.message}")
	private String token;		//token

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRepositoryTitle() {
		return repositoryTitle;
	}

	public void setRepositoryTitle(String repositoryTitle) {
		this.repositoryTitle = repositoryTitle;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
}