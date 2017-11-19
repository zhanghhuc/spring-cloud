package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: DoPortalKnowledgeCollectVo  
 * @Description: 收藏  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class DoPortalKnowledgeCollectVo {
	@NotBlank(message = "{empty.message}")
	private String knowledgeCode;//知识编号
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户编号
	@NotBlank(message = "{empty.message}")
	private String token;//token 
	@EnumType(allow={"1","2"})
	private String actionClass;//执行动作：1.收藏；2.取消收藏
	public String getKnowledgeCode() {
		return knowledgeCode;
	}
	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
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
	public String getActionClass() {
		return actionClass;
	}
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}
	
	
	

}
