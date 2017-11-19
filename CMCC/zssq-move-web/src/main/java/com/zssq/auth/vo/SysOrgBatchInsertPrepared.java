package com.zssq.auth.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class SysOrgBatchInsertPrepared implements BatchPreparedStatementSetter {

	final List<Map<String, Object>> kcOrgs;
	
	public SysOrgBatchInsertPrepared(List<Map<String, Object>> kcOrgs) {
		this.kcOrgs = kcOrgs;
	}
	
	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		
		Map<String, Object> kcOrg = kcOrgs.get(i);
		
		ps.setString(1, StringUtils.remove(UUID.randomUUID().toString(), "-"));
		ps.setString(2, kcOrg.get("ORG_CODE").toString());
		ps.setString(3, kcOrg.get("ORG_NAME").toString());
		ps.setString(4, kcOrg.get("ORG_FULL_NAME").toString());
		ps.setString(5, kcOrg.get("ORG_LEVEL").toString());
		ps.setString(6, kcOrg.get("ORG_ORDER").toString());
		
		String sourceStatus = kcOrg.get("ORG_STATUS").toString();
		ps.setByte(7, Byte.valueOf(sourceStatus));
		
		ps.setInt(8, Integer.valueOf(kcOrg.get("ORG_ID").toString()));
		ps.setString(9, kcOrg.get("PARENT_CODE").toString());
	}

	@Override
	public int getBatchSize() {
		
		return kcOrgs.size();
	}

}
