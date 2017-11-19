package com.zssq.filing.vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.zssq.shiro.mysecurity.UUIDHelper;

public class BatchSetterForUserFriendGroup implements BatchPreparedStatementSetter {

	final List<KcFriendGroup> list;

	public BatchSetterForUserFriendGroup(List<KcFriendGroup> list) {
		super();
		this.list = list;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) throws SQLException {
		KcFriendGroup kcFriendGroup = list.get(i);
		ps.setString(1, UUIDHelper.getUUID());
		ps.setString(2, kcFriendGroup.getUserCode());
		ps.setString(3, kcFriendGroup.getGroupName());
		ps.setByte(4, Byte.valueOf(kcFriendGroup.getIsDefault()));
		ps.setLong(5, kcFriendGroup.getCreateDate() == null ? System.currentTimeMillis()
				: kcFriendGroup.getCreateDate().getTime());
		ps.setLong(6, kcFriendGroup.getModifyDate() == null ? System.currentTimeMillis()
				: kcFriendGroup.getModifyDate().getTime());
		ps.setLong(7, kcFriendGroup.getId());
	}

	@Override
	public int getBatchSize() {
		return list.size();
	}

}
