package com.zssq.vote.pojo;

public class BaseTeamInfo {
    private Long id;

    private String teamCode;

    private String teamName;

    private String teamIntro;

    private String teamIcon;

    private Byte teamType;

    private Long createTime;

    private Long dissolveTime;

    private Byte isDissolve;

    private Byte isRecord;

    private String recordFileUrl;

    private String userCode;

    private String orgCode;

    private String tenantCode;

    private Integer teamId;

    private Integer orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamIntro() {
        return teamIntro;
    }

    public void setTeamIntro(String teamIntro) {
        this.teamIntro = teamIntro == null ? null : teamIntro.trim();
    }

    public String getTeamIcon() {
        return teamIcon;
    }

    public void setTeamIcon(String teamIcon) {
        this.teamIcon = teamIcon == null ? null : teamIcon.trim();
    }

    public Byte getTeamType() {
        return teamType;
    }

    public void setTeamType(Byte teamType) {
        this.teamType = teamType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getDissolveTime() {
        return dissolveTime;
    }

    public void setDissolveTime(Long dissolveTime) {
        this.dissolveTime = dissolveTime;
    }

    public Byte getIsDissolve() {
        return isDissolve;
    }

    public void setIsDissolve(Byte isDissolve) {
        this.isDissolve = isDissolve;
    }

    public Byte getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(Byte isRecord) {
        this.isRecord = isRecord;
    }

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl == null ? null : recordFileUrl.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}