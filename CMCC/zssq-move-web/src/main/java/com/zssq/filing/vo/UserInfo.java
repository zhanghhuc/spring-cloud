package com.zssq.filing.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: UserInfo  
 * @Description: 用户实体  
 * @author CaiZhaohui  
 * @date 2017年5月23日  
 *
 */
public class UserInfo implements RowMapper<UserInfo>,Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserCode(rs.getString("user_code"));
		return userInfo;
	}
	
	
	
}
