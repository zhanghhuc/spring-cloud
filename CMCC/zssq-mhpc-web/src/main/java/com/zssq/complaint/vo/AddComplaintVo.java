package com.zssq.complaint.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

public class AddComplaintVo implements Serializable{

	private static final long serialVersionUID = 3942460874041901778L;

	@NotBlank(message = "{empty.message}")
	private String infoCode;
	
	@NotBlank(message = "{empty.message}")
	private String infoUrl;
	
	@EnumType(allow={"1","2","3","4","5","6","7","8","9","10"})
	private String application;
	
	@NotBlank(message = "{empty.message}")
	private String publisherCode;
	
	@NotBlank(message = "{empty.message}")
	private String publishTime;
	
	@EnumType(allow={"1","2","3","4","5"})
	private String reason;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	
	public String getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}
	public String getInfoUrl() {
		return infoUrl;
	}
	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getPublisherCode() {
		return publisherCode;
	}
	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
