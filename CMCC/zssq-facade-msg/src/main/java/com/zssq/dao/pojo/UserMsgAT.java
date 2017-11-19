package com.zssq.dao.pojo;

import com.zssq.pojo.BasePage;

public class UserMsgAT  extends BasePage{
    private Long id;

    private String atCode;

    private String userCode;

    private String atUserCode;

    private String content;

    private Byte atType;

    private Byte isDelete;

    private String originalCode;

    private Long createTime;

    private String tenantCode;

    private String orgCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtCode() {
        return atCode;
    }

    public void setAtCode(String atCode) {
        this.atCode = atCode == null ? null : atCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getAtUserCode() {
        return atUserCode;
    }

    public void setAtUserCode(String atUserCode) {
        this.atUserCode = atUserCode == null ? null : atUserCode.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getAtType() {
        return atType;
    }

    public void setAtType(Byte atType) {
        this.atType = atType;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode == null ? null : originalCode.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}