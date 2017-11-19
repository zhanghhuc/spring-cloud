package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: IntegralAndCoin  
 * @Description: 班组积分、金币排行榜输出参数  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralAndCoin implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 根据账户类型，可存放的值包含：用户编号/班组编号/组织机构编号 */
	private String accountCode;

	/** 账户类型：1-个人 ，2-班组， 3-公司 */
	private Byte accountType;
	
	/** 积分余额 */
	private Integer integralBalance;
	
	/** 金币余额 */
	private Integer coinBalance;
	
	/** 伪列 */
	private Integer rownum;

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

	public Integer getIntegralBalance() {
		return integralBalance;
	}

	public void setIntegralBalance(Integer integralBalance) {
		this.integralBalance = integralBalance;
	}

	public Integer getCoinBalance() {
		return coinBalance;
	}

	public void setCoinBalance(Integer coinBalance) {
		this.coinBalance = coinBalance;
	}

	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}
	
}
