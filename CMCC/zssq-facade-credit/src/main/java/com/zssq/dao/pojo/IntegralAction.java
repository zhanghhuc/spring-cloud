package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

/**
 * 
 * @ClassName: IntegralAction  
 * @Description: 积分行为实体  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralAction extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/** 积分行为编号 */
	private String actionCode;
	
	/** 积分行为描述 */
	private String actionRemark;
	
	/** 积分行为类型：1-个人，2-班组，3-公司*/
	private Byte actionType;
	
	/** 周期范围：1-天，2-周 */
	private Byte actionCycle;
	
	/** 期内奖励次数 */
	private Byte actionCycleCount;
	
	/** 赠送经验值 */
	private Byte expValue;
	
	/** 奖励积分 */
	private Byte integralValue;
	
	/** 奖励金币 */
	private Byte coinValue;
	
	/** 创建时间 */
	private Long createTime;
	
	/** 修改时间 */
	private Long modifyTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionRemark() {
		return actionRemark;
	}

	public void setActionRemark(String actionRemark) {
		this.actionRemark = actionRemark;
	}

	public Byte getActionType() {
		return actionType;
	}

	public void setActionType(Byte actionType) {
		this.actionType = actionType;
	}

	public Byte getActionCycle() {
		return actionCycle;
	}

	public void setActionCycle(Byte actionCycle) {
		this.actionCycle = actionCycle;
	}

	public Byte getActionCycleCount() {
		return actionCycleCount;
	}

	public void setActionCycleCount(Byte actionCycleCount) {
		this.actionCycleCount = actionCycleCount;
	}

	public Byte getExpValue() {
		return expValue;
	}

	public void setExpValue(Byte expValue) {
		this.expValue = expValue;
	}

	public Byte getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Byte integralValue) {
		this.integralValue = integralValue;
	}

	public Byte getCoinValue() {
		return coinValue;
	}

	public void setCoinValue(Byte coinValue) {
		this.coinValue = coinValue;
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
	
	
}
