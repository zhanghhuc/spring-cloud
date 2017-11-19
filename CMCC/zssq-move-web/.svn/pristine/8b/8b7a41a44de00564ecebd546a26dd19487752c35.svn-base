package com.zssq.credit.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class MyBatchPreparedStatementSetterForCoin implements BatchPreparedStatementSetter {
	
	final List<? extends GoldSta> userOrTeamlist;
	
	public MyBatchPreparedStatementSetterForCoin(List<? extends GoldSta> userOrTeamlist) {
		this.userOrTeamlist = userOrTeamlist;
	}

	@Override
	public int getBatchSize() {
		return userOrTeamlist.size();
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		GoldSta goldSta = (GoldSta)userOrTeamlist.get(i);
		if(goldSta instanceof GoldUserSta){
			GoldUserSta goldUserSta = (GoldUserSta)goldSta;
			ps.setString(1, goldUserSta.getUserCode());
			ps.setByte(2, (byte)1);
			ps.setInt(3, goldUserSta.getGoldCoin().intValue());
			ps.setLong(4, goldUserSta.getCreateTime().getTime());
			ps.setLong(5, goldUserSta.getUpdateTime().getTime());
			ps.setString(6, goldUserSta.getOrgCode());
		}else if(goldSta instanceof GoldTeamSta){
			GoldTeamSta goldTeamSta = (GoldTeamSta)goldSta;
			ps.setString(1, goldTeamSta.getTeamCode());
			ps.setByte(2, (byte)2);
			ps.setInt(3, goldTeamSta.getGoldCoin().intValue());
			ps.setLong(4, goldTeamSta.getCreateTime().getTime());
			ps.setLong(5, goldTeamSta.getUpdateTime().getTime());
			ps.setString(6, goldTeamSta.getOrgCode());
		}
	}

}
