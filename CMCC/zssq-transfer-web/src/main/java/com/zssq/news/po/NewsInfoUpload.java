package com.zssq.news.po;

public class NewsInfoUpload {
    private Long id;

    private String infoUploadCode;

    private String newsCode;

    private Byte infoType;

    private String infoPath;

    private String infoOrgName;

    private String infoNewName;

    private String infoOrgExt;

    private String infoNewExt;

    private Long infoUploadTime;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String tenantCode;

    private Byte orgLevel;

    private String orgCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoUploadCode() {
        return infoUploadCode;
    }

    public void setInfoUploadCode(String infoUploadCode) {
        this.infoUploadCode = infoUploadCode == null ? null : infoUploadCode.trim();
    }

    public String getNewsCode() {
        return newsCode;
    }

    public void setNewsCode(String newsCode) {
        this.newsCode = newsCode == null ? null : newsCode.trim();
    }

    public Byte getInfoType() {
        return infoType;
    }

    public void setInfoType(Byte infoType) {
        this.infoType = infoType;
    }

    public String getInfoPath() {
        return infoPath;
    }

    public void setInfoPath(String infoPath) {
        this.infoPath = infoPath == null ? null : infoPath.trim();
    }

    public String getInfoOrgName() {
        return infoOrgName;
    }

    public void setInfoOrgName(String infoOrgName) {
        this.infoOrgName = infoOrgName == null ? null : infoOrgName.trim();
    }

    public String getInfoNewName() {
        return infoNewName;
    }

    public void setInfoNewName(String infoNewName) {
        this.infoNewName = infoNewName == null ? null : infoNewName.trim();
    }

    public String getInfoOrgExt() {
        return infoOrgExt;
    }

    public void setInfoOrgExt(String infoOrgExt) {
        this.infoOrgExt = infoOrgExt == null ? null : infoOrgExt.trim();
    }

    public String getInfoNewExt() {
        return infoNewExt;
    }

    public void setInfoNewExt(String infoNewExt) {
        this.infoNewExt = infoNewExt == null ? null : infoNewExt.trim();
    }

    public Long getInfoUploadTime() {
        return infoUploadTime;
    }

    public void setInfoUploadTime(Long infoUploadTime) {
        this.infoUploadTime = infoUploadTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    public Byte getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Byte orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}