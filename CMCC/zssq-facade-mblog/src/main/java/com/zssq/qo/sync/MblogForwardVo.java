package com.zssq.qo.sync;

import java.io.Serializable;

/**
 * 
    * @ClassName: MblogForwardVo  
    * @Description: 微博转发实体类  
    * @author Mr.B  
    * @date 2017年3月30日  
    *
 */
public class MblogForwardVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 转发微博CODE **/
	private String forMblogCode;
	/** 当前微博CODE **/
	private String curMblogCode;
	/** 当前微博用户CODE **/
	private String userCode;
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getForMblogCode() {
		return forMblogCode;
	}
	public void setForMblogCode(String forMblogCode) {
		this.forMblogCode = forMblogCode;
	}
	public String getCurMblogCode() {
		return curMblogCode;
	}
	public void setCurMblogCode(String curMblogCode) {
		this.curMblogCode = curMblogCode;
	}
}
