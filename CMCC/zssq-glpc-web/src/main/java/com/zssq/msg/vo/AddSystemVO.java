package com.zssq.msg.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: AddSystemVO  
 * @Description: 添加系统消息  
 * @author YDB  
 * @date 2017年4月11日  
 *
 */
public class AddSystemVO {

	
	
	//发布人code
	@NotBlank(message = "{empty.message}")
	private String userCode;
	//机构编号	
	@NotBlank(message = "{empty.message}")
	private String orgCode;
/*	//显示级别code
	@NotBlank(message = "{empty.message}")
	private String showOrgCode;*/
	//标题
	@NotBlank(message = "{empty.message}")
	private String infoTitle;
	//内容
	@NotBlank(message = "{empty.message}")
	private String infoContent;
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	
	
	
}