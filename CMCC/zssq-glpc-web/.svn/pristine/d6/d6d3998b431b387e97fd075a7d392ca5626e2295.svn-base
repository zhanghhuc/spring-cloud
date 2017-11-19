package com.zssq.knowledgeLib.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.LongType;
/**
 * 
 * @Description: 热点列表Vo  
 * @author sry  
 * @date 2017年4月17日  
 *
 */
public class GetKnowledgeLibListForGLVO implements Serializable {
	
	
	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String orgCode;	//门户编号
	
	@LongType(expression = ">0")
	private Long queryTime;//首次查询时间，前端生成传入
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户的userCode
	
	@NotBlank(message = "{empty.message}")
	private String token;	//token
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Long getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Long queryTime) {
		this.queryTime = queryTime;
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