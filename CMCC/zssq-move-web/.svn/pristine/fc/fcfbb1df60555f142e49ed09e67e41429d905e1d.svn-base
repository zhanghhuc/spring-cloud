package com.zssq.credit.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: OrgInfo  
 * @Description: 组织机构信息实体
 * @author CaiZhaohui  
 * @date 2017年5月31日  
 *
 */
public class OrgInfo implements RowMapper<OrgInfo>,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 所属行政组织编号 */
	private String manOrgCode;

	public String getManOrgCode() {
		return manOrgCode;
	}

	public void setManOrgCode(String manOrgCode) {
		this.manOrgCode = manOrgCode;
	}

	@Override
	public OrgInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrgInfo orgInfo = new OrgInfo();
		orgInfo.setManOrgCode(rs.getString("man_org_code"));
		return orgInfo;
	}

}
