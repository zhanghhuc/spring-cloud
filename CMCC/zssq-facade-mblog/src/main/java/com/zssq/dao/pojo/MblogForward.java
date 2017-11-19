package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
    * @ClassName: MblogForward  
    * @Description: 微博转发历程记录表  
    * @author Mr.B  
    * @date 2017年3月30日  
    *
 */
public class MblogForward implements Serializable{
    /**  
    * @Fields serialVersionUID
    */  
	private static final long serialVersionUID = 1L;

	private String curMblogCode = "";
	private String sourceMblogCode = "";
	private String userCode = "";
	
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getCurMblogCode() {
		return curMblogCode;
	}
	public void setCurMblogCode(String curMblogCode) {
		this.curMblogCode = curMblogCode;
	}
	public String getSourceMblogCode() {
		return sourceMblogCode;
	}
	public void setSourceMblogCode(String sourceMblogCode) {
		this.sourceMblogCode = sourceMblogCode;
	}
	
	
}
