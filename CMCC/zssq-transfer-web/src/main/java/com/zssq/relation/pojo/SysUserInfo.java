package com.zssq.relation.pojo;

public class SysUserInfo {
    private Long id;

    private String userCode;

    private String userName;

    private Byte userSex;

    private String userOfficePhone;

    private String orgCode;

    private Byte userStatus;

    private String saasTenantCode;

    private String userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getUserSex() {
        return userSex;
    }

    public void setUserSex(Byte userSex) {
        this.userSex = userSex;
    }

    public String getUserOfficePhone() {
        return userOfficePhone;
    }

    public void setUserOfficePhone(String userOfficePhone) {
        this.userOfficePhone = userOfficePhone == null ? null : userOfficePhone.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Byte getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Byte userStatus) {
        this.userStatus = userStatus;
    }

    public String getSaasTenantCode() {
        return saasTenantCode;
    }

    public void setSaasTenantCode(String saasTenantCode) {
        this.saasTenantCode = saasTenantCode == null ? null : saasTenantCode.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}