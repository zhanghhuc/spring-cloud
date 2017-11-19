package com.zssq.knowledgeLib.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: DoPortalKnowledgePraiseVo  
 * @Description: 点赞vo  
 * @author sry  
 * @date 2017年5月2日  
 *
 */
public class DoPortalKnowledgePraiseVo {
	
	@NotBlank(message = "{empty.message}")
	private String objectCode;//被点赞对象code
	@EnumType(allow={"1","2"})
	private String actionType;//点赞类型 操作类型：1.知识；2.追加知识
	@EnumType(allow={"1","2"})
	private String actionClass;//点赞动作 执行动作：1.点赞；2.取消点赞
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户编码
	@NotBlank(message = "{empty.message}")
	private String token;//token
	public String getObjectCode() {
		return objectCode;
	}
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionClass() {
		return actionClass;
	}
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
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
