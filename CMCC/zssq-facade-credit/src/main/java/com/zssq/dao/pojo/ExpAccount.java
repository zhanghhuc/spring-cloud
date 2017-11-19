package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: ExpAccount  
 * @Description: 经验值账户实体  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class ExpAccount implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/** 根据账户类型，可存放的值包含：用户编号/班组编号/组织机构编号 */
	private String accountCode;
	
	/** 当前经验值 */
	private Integer currentExp;
	
	/** 当前等级{@link UserLevelConfig} */
	private Integer currentLevel;
	
	/** 创建时间 */
	private Long createTime;
	
	/** 修改时间 */
	private Long modifyTime;
	
	/** 所属行政组织编号 */
	private String orgCode;
	
	/** 租户编号 */
	private String saasTenantCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Integer getCurrentExp() {
		return currentExp;
	}

	public void setCurrentExp(Integer currentExp) {
		this.currentExp = currentExp;
	}

	public Integer getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSaasTenantCode() {
		return saasTenantCode;
	}

	public void setSaasTenantCode(String saasTenantCode) {
		this.saasTenantCode = saasTenantCode;
	}
	
}
