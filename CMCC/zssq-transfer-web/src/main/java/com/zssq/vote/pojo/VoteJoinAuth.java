package com.zssq.vote.pojo;

public class VoteJoinAuth {
    private Long id;

    private String code;

    private Byte isDisable;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String voteInfoCode;

    private Byte rangeType;

    private Byte isCascade;

    private String gRangeCode;

    private String gRangeName;

    private String pRangeCode;

    private String pRangeName;

    private String cRangeCode;

    private String cRangeName;

    private String tRangeCode;

    private String tRangeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Byte isDisable) {
        this.isDisable = isDisable;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
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

    public Byte getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Byte orgLevel) {
        this.orgLevel = orgLevel;
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

    public String getVoteInfoCode() {
        return voteInfoCode;
    }

    public void setVoteInfoCode(String voteInfoCode) {
        this.voteInfoCode = voteInfoCode == null ? null : voteInfoCode.trim();
    }

    public Byte getRangeType() {
        return rangeType;
    }

    public void setRangeType(Byte rangeType) {
        this.rangeType = rangeType;
    }

    public Byte getIsCascade() {
        return isCascade;
    }

    public void setIsCascade(Byte isCascade) {
        this.isCascade = isCascade;
    }

    public String getgRangeCode() {
        return gRangeCode;
    }

    public void setgRangeCode(String gRangeCode) {
        this.gRangeCode = gRangeCode == null ? null : gRangeCode.trim();
    }

    public String getgRangeName() {
        return gRangeName;
    }

    public void setgRangeName(String gRangeName) {
        this.gRangeName = gRangeName == null ? null : gRangeName.trim();
    }

    public String getpRangeCode() {
        return pRangeCode;
    }

    public void setpRangeCode(String pRangeCode) {
        this.pRangeCode = pRangeCode == null ? null : pRangeCode.trim();
    }

    public String getpRangeName() {
        return pRangeName;
    }

    public void setpRangeName(String pRangeName) {
        this.pRangeName = pRangeName == null ? null : pRangeName.trim();
    }

    public String getcRangeCode() {
        return cRangeCode;
    }

    public void setcRangeCode(String cRangeCode) {
        this.cRangeCode = cRangeCode == null ? null : cRangeCode.trim();
    }

    public String getcRangeName() {
        return cRangeName;
    }

    public void setcRangeName(String cRangeName) {
        this.cRangeName = cRangeName == null ? null : cRangeName.trim();
    }

    public String gettRangeCode() {
        return tRangeCode;
    }

    public void settRangeCode(String tRangeCode) {
        this.tRangeCode = tRangeCode == null ? null : tRangeCode.trim();
    }

    public String gettRangeName() {
        return tRangeName;
    }

    public void settRangeName(String tRangeName) {
        this.tRangeName = tRangeName == null ? null : tRangeName.trim();
    }
}