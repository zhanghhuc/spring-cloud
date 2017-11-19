package com.zssq.knowledgeLib.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @Description: 热点列表Vo  
 * @author guoyang  
 * @date 2017年5月2日  
 *
 */
public class GetKnowledgeInfoForGLVO implements Serializable {
	
	
	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String contentCode;	//门户编号
	
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户的userCode
	
	@NotBlank(message = "{empty.message}")
	private String token;	//token

	public String getContentCode() {
		return contentCode;
	}

	public void setContentCode(String contentCode) {
		this.contentCode = contentCode;
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