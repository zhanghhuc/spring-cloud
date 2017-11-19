package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: UserLevelConfig  
 * @Description: 用户经验值与等级等信息的实体  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class UserLevelConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	/** 等级编号 */
	private String levelCode;
	
	/** 等级 */
	private Byte levelNo;
	
	/** 经验值 */
	private Integer expValue;
	
	/** 等级称谓 */
	private String levelTitle;
	
	/** 租户编号 */
	private String saasTenantCode;
	
	/** 起始经验值 */
	private Integer expValueStart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public Byte getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(Byte levelNo) {
		this.levelNo = levelNo;
	}

	public Integer getExpValue() {
		return expValue;
	}

	public void setExpValue(Integer expValue) {
		this.expValue = expValue;
	}

	public String getLevelTitle() {
		return levelTitle;
	}

	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}

	public String getSaasTenantCode() {
		return saasTenantCode;
	}

	public void setSaasTenantCode(String saasTenantCode) {
		this.saasTenantCode = saasTenantCode;
	}

	public Integer getExpValueStart() {
		return expValueStart;
	}

	public void setExpValueStart(Integer expValueStart) {
		this.expValueStart = expValueStart;
	}
	
}
