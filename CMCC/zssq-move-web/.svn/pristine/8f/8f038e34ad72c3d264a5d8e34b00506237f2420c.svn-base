package com.zssq.filing.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.zssq.shiro.mysecurity.UUIDHelper;

public class BatchSetterForUserFriend implements BatchPreparedStatementSetter {

	final List<KcFriendGrpUsrRel> list;

	public BatchSetterForUserFriend(List<KcFriendGrpUsrRel> list) {
		super();
		this.list = list;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		KcFriendGrpUsrRel kcFriendGrpUsrRel = list.get(i);
		ps.setString(1, UUIDHelper.getUUID());
		ps.setString(2, kcFriendGrpUsrRel.getUserCode());
		ps.setString(3, kcFriendGrpUsrRel.getFriendCode());
		ps.setString(4, kcFriendGrpUsrRel.getGroupCode());
		ps.setLong(5, kcFriendGrpUsrRel.getCreateDate() == null ? System.currentTimeMillis()
				: kcFriendGrpUsrRel.getCreateDate().getTime());
		ps.setLong(6, kcFriendGrpUsrRel.getCreateDate() == null ? System.currentTimeMillis()
				: kcFriendGrpUsrRel.getCreateDate().getTime());
	}

	@Override
	public int getBatchSize() {
		return list.size();
	}

}
