package com.zssq.pojo;

import java.io.Serializable;

import com.zssq.utils.PropertiesUtil;

public class ResultJSON implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String returnCode;
	private String returnTip;
	//private JSONObject body = new JSONObject();
	
	private Object body = new Object();
	
	public ResultJSON() {

	}
	
	public ResultJSON(String messageCode, Object... args) {
		String tip = "";
		try {
			Message m = PropertiesUtil.getMessage(messageCode);
			tip = m.getTip();
			messageCode = m.getCode();
		} catch (Exception e) {
			tip = "未定义异常信息";
		}
		this.returnCode = messageCode;
		this.returnTip = String.format(tip, args);
	}
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnTip() {
		return returnTip;
	}
	public void setReturnTip(String returnTip) {
		this.returnTip = returnTip;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}

}
