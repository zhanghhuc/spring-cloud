package com.zssq.filing.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserFriendGroup implements RowMapper<UserFriendGroup>, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String friendGroupCode;

	private String userCode;

	private String groupName;

	private Byte isDefault;

	private Long createTime;

	private Long modifyTime;

	private String orgCode;

	private Byte orgLevel;
	
	private Long groupId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFriendGroupCode() {
		return friendGroupCode;
	}

	public void setFriendGroupCode(String friendGroupCode) {
		this.friendGroupCode = friendGroupCode == null ? null : friendGroupCode.trim();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode == null ? null : userCode.trim();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public Byte getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public Byte getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Byte orgLevel) {
		this.orgLevel = orgLevel;
	}
	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Override
	public UserFriendGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserFriendGroup userFriendGroup = new UserFriendGroup();
		userFriendGroup.setId(rs.getLong("id"));
		userFriendGroup.setFriendGroupCode(rs.getString("friend_group_code"));
		userFriendGroup.setUserCode(rs.getString("user_code"));
		userFriendGroup.setGroupName(rs.getString("group_name"));
		userFriendGroup.setIsDefault(rs.getByte("is_default"));
		userFriendGroup.setCreateTime(rs.getLong("create_time"));
		userFriendGroup.setModifyTime(rs.getLong("modify_time"));
		userFriendGroup.setOrgCode(rs.getString("org_code"));
		userFriendGroup.setOrgLevel(rs.getByte("org_level"));
		userFriendGroup.setGroupId(rs.getLong("GROUP_ID"));
		return userFriendGroup;
	}
}