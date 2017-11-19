package com.zssq.qo.sync;

import java.io.Serializable;

/**
 * 
    * @ClassName: MblogEssenceVo  
    * @Description: 微博置精VO  
    * @author Mr.B  
    * @date 2017年3月31日  
    *
 */
public class MblogEssenceVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 微博CODE **/
	private String mblogCode;
	/** 范围 **/
	private Byte scope;
	/** 是否置精 **/
	private Byte isEssence;
	/** 用户CODE **/
	private String userCode;
	
	
	public String getMblogCode() {
		return mblogCode;
	}
	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}
	public Byte getScope() {
		return scope;
	}
	public void setScope(Byte scope) {
		this.scope = scope;
	}
	public Byte getIsEssence() {
		return isEssence;
	}
	public void setIsEssence(Byte isEssence) {
		this.isEssence = isEssence;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	
}
