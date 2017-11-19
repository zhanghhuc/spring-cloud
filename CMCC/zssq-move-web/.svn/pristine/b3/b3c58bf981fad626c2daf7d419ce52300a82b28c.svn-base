package com.zssq.credit.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: IntegralAccount  
 * @Description: 积分账户实体  
 * @author power  
 * @date 2017年4月27日  
 *
 */
public class IntegralAccount implements RowMapper<IntegralAccount>,Serializable {

	private static final long serialVersionUID = 1L;

	/** 自增主键 */
	private Long id;
	
	/** 根据账户类型，可存放的值包含：用户编号/班组编号/组织机构编号 */
	private String accountCode;
	
	/** 账户类型：1-个人 ，2-班组， 3-公司 */
	private Byte accountType;
	
	/** 积分余额 */
	private Integer integralBalance;
	
	/** 积分余额校验盐值：MD5，使用当前积分余额值和账户编号生成 */
	private String integralBalanceSalt;
	
	/** 创建时间 */
	private Long createTime;
	
	/** 修改时间 */
	private Long modifyTime;
	
	/** 所属行政组织编码 */
	private String orgCode;
	
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

	public Integer getIntegralBalance() {
		return integralBalance;
	}

	public void setIntegralBalance(Integer integralBalance) {
		this.integralBalance = integralBalance;
	}

	public String getIntegralBalanceSalt() {
		return integralBalanceSalt;
	}

	public void setIntegralBalanceSalt(String integralBalanceSalt) {
		this.integralBalanceSalt = integralBalanceSalt;
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
	public IntegralAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		IntegralAccount integralAccount = new IntegralAccount();
		integralAccount.setId(rs.getLong("id"));
		integralAccount.setAccountCode(rs.getString("account_code"));
		integralAccount.setAccountType(rs.getByte("account_type"));
		integralAccount.setIntegralBalance(rs.getInt("integral_balance"));
		integralAccount.setIntegralBalanceSalt(rs.getString("integral_balance_salt"));
		integralAccount.setCreateTime(rs.getLong("create_time"));
		integralAccount.setModifyTime(rs.getLong("modify_time"));
		integralAccount.setOrgCode(rs.getString("org_code"));
		integralAccount.setSaasTenantCode(rs.getString("saas_tenant_code"));
		return integralAccount;
	}

}
