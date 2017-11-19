package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class UserFriend extends BasePage {

	private static final long serialVersionUID = 1L;

	private Long id;

    private String userFriendCode;

    private String userCode;

    private String friendUserCode;

    private String friendGroupCode;

    private Long createTime;

    private Long modifyTime;

    private String orgCode;

    private Byte orgLevel;
    
    private String groupName;

    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFriendCode() {
        return userFriendCode;
    }

    public void setUserFriendCode(String userFriendCode) {
        this.userFriendCode = userFriendCode == null ? null : userFriendCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getFriendUserCode() {
        return friendUserCode;
    }

    public void setFriendUserCode(String friendUserCode) {
        this.friendUserCode = friendUserCode == null ? null : friendUserCode.trim();
    }

    public String getFriendGroupCode() {
        return friendGroupCode;
    }

    public void setFriendGroupCode(String friendGroupCode) {
        this.friendGroupCode = friendGroupCode == null ? null : friendGroupCode.trim();
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
}