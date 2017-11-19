package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: BlogDraft  
 * @Description: 博客草稿  
 * @author ZKZ  
 * @date 2017年3月24日  
 *
 */
public class BlogDraft implements Serializable {
        
	private static final long serialVersionUID = 1L;

	private Long id = 0L;
    private String draftCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String userCode = "";
    private Byte draftDepend = 0;
    private String teamCode = "";
    private String draftDigest = "";
    private String draftTitle = "";
    private String classCode = "";
    private String draftTags = "";
    private Long draftPlanPublishTime = 0L;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDraftCode() {
        return draftCode;
    }

    public void setDraftCode(String draftCode) {
        this.draftCode = draftCode == null ? null : draftCode.trim();
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Byte getDraftDepend() {
        return draftDepend;
    }

    public void setDraftDepend(Byte draftDepend) {
        this.draftDepend = draftDepend;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public String getDraftDigest() {
        return draftDigest;
    }

    public void setDraftDigest(String draftDigest) {
        this.draftDigest = draftDigest == null ? null : draftDigest.trim();
    }

    public String getDraftTitle() {
        return draftTitle;
    }

    public void setDraftTitle(String draftTitle) {
        this.draftTitle = draftTitle == null ? null : draftTitle.trim();
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getDraftTags() {
        return draftTags;
    }

    public void setDraftTags(String draftTags) {
        this.draftTags = draftTags == null ? null : draftTags.trim();
    }

    public Long getDraftPlanPublishTime() {
        return draftPlanPublishTime;
    }

    public void setDraftPlanPublishTime(Long draftPlanPublishTime) {
        this.draftPlanPublishTime = draftPlanPublishTime;
    }
}