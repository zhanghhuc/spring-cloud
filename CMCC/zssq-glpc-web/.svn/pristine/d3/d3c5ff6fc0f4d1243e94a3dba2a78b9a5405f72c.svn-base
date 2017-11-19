package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * 
    * @ClassName: SubmitOrNotExamineVo  
    * @Description: 提交/撤销活动审核 
    * @author liuzhijie  
    * @date 2017年4月18日  
    *
 */
public class SubmitOrNotExamineVo implements Serializable {

	private static final long serialVersionUID = -60330756568118781L;

	@NotBlank(message = "{empty.message}")
	private String templateCode; //模板CODE
	
	@NotBlank(message = "{empty.message}")
	private String activityCode; //活动CODE
	
	@EnumType(required=true ,allow={"1","2"})
	private String operateType; //操作类型：1-提交审核  2-撤销审核
	
	@NotBlank(message = "{empty.message}")
	private String userCode; //当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
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
