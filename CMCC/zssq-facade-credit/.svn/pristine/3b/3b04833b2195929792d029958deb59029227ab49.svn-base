package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: CoinEntity  
 * @Description: 此金币实体作用：积分行为表中配置的积分行为以外的动作导致的金币账户的维护
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class CoinEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 根据用户行为主体的不同，该字段可存放：用户编号/班组编号/组织编号 */
	private String accountCode;
	
	/** 账户类型，配合 accountCode 属性使用，用以区分账户类型；1-个人 ，2-班组， 3-公司 */
	private Byte accountType;
	
	/** 具体行为描述 */
	private String actionComment;
	
	/** 金币增减值 */
	private Integer value;
	
	/** 行政组织编号 */
	private String orgCode;

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	public Byte getAccountType() {
		return accountType;
	}

	public void setAccountType(Byte accountType) {
		this.accountType = accountType;
	}

	public String getActionComment() {
		return actionComment;
	}

	public void setActionComment(String actionComment) {
		this.actionComment = actionComment;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}
