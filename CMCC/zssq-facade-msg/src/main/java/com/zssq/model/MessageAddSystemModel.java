package com.zssq.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: MessageAddSystemModel  
 * @Description: 添加系统消息  
 * @author YDB  
 * @date 2017年4月5日  
 *
 */
public class MessageAddSystemModel implements Serializable {
	
	//发布人code
	private String userCode;
	//租户编号
	private String tenantCode;
	//机构编号	
	private String orgCode;
	//显示级别code
	private String showOrgCode;
	//标题
	private String title;
	//内容
	private String content;
	//显示级别
	private int showType;
	
	
	
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getShowOrgCode() {
		return showOrgCode;
	}
	public void setShowOrgCode(String showOrgCode) {
		this.showOrgCode = showOrgCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
