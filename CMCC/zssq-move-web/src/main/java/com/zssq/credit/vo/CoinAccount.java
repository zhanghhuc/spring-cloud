package com.zssq.credit.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: CoinAccount  
 * @Description: 金币账户实体  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class CoinAccount implements RowMapper<CoinAccount>,Serializable {

	private static final long serialVersionUID = 1L;

	/** 自增主键 */
	private Long id;

	/** 根据账户类型，可存放的值包含：用户编号/班组编号/组织机构编号 */
	private String accountCode;
	
	/** 账户类型 */
	private Byte accountType;
	
	/** 金币余额 */
	private Integer coinBalance;
	
	/** 金币余额盐值 */
	private String coinBalanceSalt;
	
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
	
	public Byte getAccountType() {
		return accountType;
	}

	public void setAccountType(Byte accountType) {
		this.accountType = accountType;
	}

	public Integer getCoinBalance() {
		return coinBalance;
	}

	public void setCoinBalance(Integer coinBalance) {
		this.coinBalance = coinBalance;
	}

	public String getCoinBalanceSalt() {
		return coinBalanceSalt;
	}

	public void setCoinBalanceSalt(String coinBalanceSalt) {
		this.coinBalanceSalt = coinBalanceSalt;
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

	@Override
	public CoinAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		CoinAccount coinAccount = new CoinAccount();
		coinAccount.setId(rs.getLong("id"));
		coinAccount.setAccountCode(rs.getString("account_code"));
		coinAccount.setAccountType(rs.getByte("account_type"));
		coinAccount.setCoinBalance(rs.getInt("coin_balance"));
		coinAccount.setCoinBalanceSalt(rs.getString("coin_balance_salt"));
		coinAccount.setCreateTime(rs.getLong("create_time"));
		coinAccount.setModifyTime(rs.getLong("modify_time"));
		coinAccount.setOrgCode(rs.getString("org_code"));
		coinAccount.setSaasTenantCode(rs.getString("saas_tenant_code"));
		return coinAccount;
	}
	
}
