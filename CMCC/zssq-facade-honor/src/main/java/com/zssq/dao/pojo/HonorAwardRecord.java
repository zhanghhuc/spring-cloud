package com.zssq.dao.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HonorAwardRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String honorAwardRecordCode;

    private Byte isDisable;

    private Byte isDelete;

    private String tenantCode;

    private String orgCode;

    private Byte orgLevel;

    private Long createTime;

    private Long modifyTime;

    private String remark;

    private String honorCode;

    private String honorName;

    private String honorUrl;

    private String agentCode;

    private String awarderCode;

    private String awarderName;

    private String awarderPosition;

    private Byte honoreeType;

    private String honoreeCode;

    private String awardReason;

    private Byte isRevoked;

    private Long revokedTime;

    private Integer commentCount;

    private Integer praiseCount;
    
    private String dynamicCode;
    // 评论列表
    private List<HonorComment> commentList = new ArrayList<>();
    
    public List<HonorComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<HonorComment> commentList) {
		this.commentList = commentList;
	}

	public String getDynamicCode() {
		return dynamicCode;
	}

	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}

	//点赞人code
    private String admirerCode; 
    
    //加载更多时 每次加载的数据条数
    private Integer pageSize;
    
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getAdmirerCode() {
		return admirerCode;
	}

	public void setAdmirerCode(String admirerCode) {
		this.admirerCode = admirerCode;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHonorAwardRecordCode() {
        return honorAwardRecordCode;
    }

    public void setHonorAwardRecordCode(String honorAwardRecordCode) {
        this.honorAwardRecordCode = honorAwardRecordCode == null ? null : honorAwardRecordCode.trim();
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

    public String getHonorCode() {
        return honorCode;
    }

    public void setHonorCode(String honorCode) {
        this.honorCode = honorCode == null ? null : honorCode.trim();
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName == null ? null : honorName.trim();
    }

    public String getHonorUrl() {
        return honorUrl;
    }

    public void setHonorUrl(String honorUrl) {
        this.honorUrl = honorUrl == null ? null : honorUrl.trim();
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode == null ? null : agentCode.trim();
    }

    public String getAwarderCode() {
        return awarderCode;
    }

    public void setAwarderCode(String awarderCode) {
        this.awarderCode = awarderCode == null ? null : awarderCode.trim();
    }

    public String getAwarderName() {
        return awarderName;
    }

    public void setAwarderName(String awarderName) {
        this.awarderName = awarderName == null ? null : awarderName.trim();
    }

    public String getAwarderPosition() {
        return awarderPosition;
    }

    public void setAwarderPosition(String awarderPosition) {
        this.awarderPosition = awarderPosition == null ? null : awarderPosition.trim();
    }

    public Byte getHonoreeType() {
        return honoreeType;
    }

    public void setHonoreeType(Byte honoreeType) {
        this.honoreeType = honoreeType;
    }

    public String getHonoreeCode() {
        return honoreeCode;
    }

    public void setHonoreeCode(String honoreeCode) {
        this.honoreeCode = honoreeCode == null ? null : honoreeCode.trim();
    }

    public String getAwardReason() {
        return awardReason;
    }

    public void setAwardReason(String awardReason) {
        this.awardReason = awardReason == null ? null : awardReason.trim();
    }

    public Byte getIsRevoked() {
        return isRevoked;
    }

    public void setIsRevoked(Byte isRevoked) {
        this.isRevoked = isRevoked;
    }

    public Long getRevokedTime() {
        return revokedTime;
    }

    public void setRevokedTime(Long revokedTime) {
        this.revokedTime = revokedTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }
}