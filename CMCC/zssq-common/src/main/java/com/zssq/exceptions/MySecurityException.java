package com.zssq.exceptions;

/**
 * 无法登陆 返回appId
 * @author power
 *
 */
public class MySecurityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String appId = null;
	
	public MySecurityException(String appId) {
		this.appId = appId;
	}


	public String getAppId() {
		return appId;
	}
	
}
