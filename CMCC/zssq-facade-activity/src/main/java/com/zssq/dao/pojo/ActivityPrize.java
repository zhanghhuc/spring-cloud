package com.zssq.dao.pojo;

import java.io.Serializable;

public class ActivityPrize implements Serializable {
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

    private String activityCode;

    private Byte isJoinPrize;

    private Integer sequenceNo;

    private String prizeName;

    private String introduce;

    private Byte isRewardGold;

    private Integer rewardGoldNum;

    private Byte isAwardHonor;

    private String awardHonorCode;

    private String awardHonorName;

    private String awardHonorUrl;

    private String rule;
    
    //被授予奖励者Code
    private String accepterCode; 
    
    //活动名称
    private String activityName;
    
    public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getAccepterCode() {
		return accepterCode;
	}

	public void setAccepterCode(String accepterCode) {
		this.accepterCode = accepterCode;
	}

	private static final long serialVersionUID = 1L;

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

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    public Byte getIsJoinPrize() {
        return isJoinPrize;
    }

    public void setIsJoinPrize(Byte isJoinPrize) {
        this.isJoinPrize = isJoinPrize;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Byte getIsRewardGold() {
        return isRewardGold;
    }

    public void setIsRewardGold(Byte isRewardGold) {
        this.isRewardGold = isRewardGold;
    }

    public Integer getRewardGoldNum() {
        return rewardGoldNum;
    }

    public void setRewardGoldNum(Integer rewardGoldNum) {
        this.rewardGoldNum = rewardGoldNum;
    }

    public Byte getIsAwardHonor() {
        return isAwardHonor;
    }

    public void setIsAwardHonor(Byte isAwardHonor) {
        this.isAwardHonor = isAwardHonor;
    }

    public String getAwardHonorCode() {
        return awardHonorCode;
    }

    public void setAwardHonorCode(String awardHonorCode) {
        this.awardHonorCode = awardHonorCode == null ? null : awardHonorCode.trim();
    }

    public String getAwardHonorName() {
        return awardHonorName;
    }

    public void setAwardHonorName(String awardHonorName) {
        this.awardHonorName = awardHonorName == null ? null : awardHonorName.trim();
    }

    public String getAwardHonorUrl() {
        return awardHonorUrl;
    }

    public void setAwardHonorUrl(String awardHonorUrl) {
        this.awardHonorUrl = awardHonorUrl == null ? null : awardHonorUrl.trim();
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }
}