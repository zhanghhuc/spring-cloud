package com.zssq.credit.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class MyBatchPreparedStatementSetterForCoinDetail implements BatchPreparedStatementSetter {
	
	final List<CoinAccount> list;

	public MyBatchPreparedStatementSetterForCoinDetail(List<CoinAccount> list) {
		super();
		this.list = list;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		CoinAccount coinAccount = list.get(i);
		ps.setString(1, coinAccount.getAccountCode());
		ps.setByte(2, (byte)1);
		ps.setInt(3, coinAccount.getCoinBalance());
		ps.setInt(4, coinAccount.getCoinBalance());
		ps.setString(5, "-1");
		ps.setLong(6, System.currentTimeMillis());
	}

	@Override
	public int getBatchSize() {
		return list.size();
	}
	
	

}
