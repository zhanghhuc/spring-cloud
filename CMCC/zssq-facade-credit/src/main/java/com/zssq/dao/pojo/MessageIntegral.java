package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 用户行为信息，Kafka 中存放的数据结构
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
public class MessageIntegral implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户行为编号 {@link CreditConstants} */
	private String actionCode;
	
	/** 根据用户行为主体的不同，该字段可存放：用户编号/班组编号/组织编号 */
	private String accountCode;
	
	/** 账户类型，配合 accountCode 属性使用，用以区分账户类型 */
	private Byte accountType;
	
	/** 行政组织编号 */
	private String manageOrgCode;

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

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

	public String getManageOrgCode() {
		return manageOrgCode;
	}

	public void setManageOrgCode(String manageOrgCode) {
		this.manageOrgCode = manageOrgCode;
	}
}
