package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

/**
 * 
 * @ClassName: CoinAccountDetail  
 * @Description: 金币账户明细实体
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class CoinAccountDetail extends BasePage {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	/** 根据账户类型，可存放的值包含：用户编号/班组编号/组织机构编号 */
	private String accountCode;
	
	/** 金币账户明细分类：1-增加，2-减少 */
	private Byte accountDetailType;
	
	/** 本次增减值 */
	private Integer currentValue;
	
	/** 本次增减后余额 */
	private Integer currentBalance;
	
	/** 积分行为编号 */
	private String actionCode;
	
	/** 创建时间 */
	private Long createTime;
	
	/** 修改时间 */
	private String actionComment;

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

	public Byte getAccountDetailType() {
		return accountDetailType;
	}

	public void setAccountDetailType(Byte accountDetailType) {
		this.accountDetailType = accountDetailType;
	}

	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Integer currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getActionComment() {
		return actionComment;
	}

	public void setActionComment(String actionComment) {
		this.actionComment = actionComment;
	}
	
}
