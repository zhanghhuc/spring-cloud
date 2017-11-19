package com.zssq.credit.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class MyBatchPreparedStatementSetterForIntegralDetail implements BatchPreparedStatementSetter {

	final List<IntegralAccount> list;

	public MyBatchPreparedStatementSetterForIntegralDetail(List<IntegralAccount> list) {
		super();
		this.list = list;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		IntegralAccount integralAccount = list.get(i);
		ps.setString(1, integralAccount.getAccountCode());
		ps.setByte(2, (byte) 1);
		ps.setInt(3, integralAccount.getIntegralBalance());
		ps.setInt(4, integralAccount.getIntegralBalance());
		ps.setString(5, "-1");
		ps.setLong(6, System.currentTimeMillis());
	}

	@Override
	public int getBatchSize() {
		return list.size();
	}

}
