package com.zssq.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 
 * @ClassName: DeleteLibForGLForSerVO  
 * @Description: 删除知识库  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class DeleteLibForGLForSerVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;		//组织编号
	
	
	@NotBlank(message = "{empty.message}")
	private String repositoryCode;		//知识库
	
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


	public String getRepositoryCode() {
		return repositoryCode;
	}

	public void setRepositoryCode(String repositoryCode) {
		this.repositoryCode = repositoryCode;
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