package com.zssq.pojo;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String requestStatus;
	private String returnCode;
	private String returnTip;
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
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

	
	
	

}
