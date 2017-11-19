package com.zssq.relation.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserInfoVo implements RowMapper<UserInfoVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;

	private String userCode="";
	private String orgCode="";
	private String tenantCode;
	
	
	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getTenantCode() {
		return tenantCode;
	}


	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	/**
	 * 
	    * @Title: getSelectStatement  
	    * @Description: 获取查询语句
	    * @param userId
		* @return String    返回类型
	 */
	public static String getSelectStatement(String userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" user_code,saas_tenant_code,man_org_code ")
		  .append(" FROM ")
		  .append(" ( SELECT user_code,saas_tenant_code,org_code FROM transfer_user_info WHERE user_id = '"+userId+"') AS user_info ")
		  .append(" LEFT JOIN ")
		  .append(" ( SELECT sys_org_code,man_org_code FROM transfer_org_info) AS org_info ")
		  .append(" on user_info.org_code = org_info.sys_org_code ");
		return sb.toString();
	}

	@Override
	public UserInfoVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfoVo vo = new UserInfoVo();
		vo.setUserCode(rs.getString("user_code"));
		vo.setOrgCode(rs.getString("man_org_code"));
		vo.setTenantCode(rs.getString("saas_tenant_code"));
		return vo;
	}

}
