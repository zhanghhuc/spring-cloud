package com.zssq.credit.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: UserInfo  
 * @Description: 用户实体  
 * @author CaiZhaohui  
 * @date 2017年5月19日  
 *
 */
public class UserInfo implements RowMapper<UserInfo>,Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 用户编号 */
	private String userCode;
	
	/** 所属组织编号 */
	private String orgCode;

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

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserCode(rs.getString("user_code"));
		userInfo.setOrgCode(rs.getString("org_code"));
		return userInfo;
	}
	
}
