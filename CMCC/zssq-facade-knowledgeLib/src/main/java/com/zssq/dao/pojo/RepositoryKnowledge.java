package com.zssq.dao.pojo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledge  
 * @Description: 知识  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class RepositoryKnowledge implements Serializable{
        
	private static final long serialVersionUID = 7400808880524628116L;

	private Long id = 0L;

    private String knowledgeCode = "";

    private String tenantCode = "";

    private String orgCode = "";

    private Long createTime = 0L;

    private Long modifyTime = 0L;

    private Long addTime = 0L;

    private Long appendTime = 0L;

    private String remark = "";

    private String repositoryCode = "";

    private String userCode = "";

    private String knowledgeTitle = "";

    private String knowledgeDigest = "";

    private String contentCode = "";

    private Integer shareNum = 0;

    private Integer praiseNum = 0;

    private Integer collectNum = 0;

    private Integer appendNum = 0;

    private Byte isDelete = 0;
    
    private Byte canOpen = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKnowledgeCode() {
        return knowledgeCode;
    }

    public void setKnowledgeCode(String knowledgeCode) {
        this.knowledgeCode = knowledgeCode == null ? null : knowledgeCode.trim();
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

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getAppendTime() {
        return appendTime;
    }

    public void setAppendTime(Long appendTime) {
        this.appendTime = appendTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRepositoryCode() {
        return repositoryCode;
    }

    public void setRepositoryCode(String repositoryCode) {
        this.repositoryCode = repositoryCode == null ? null : repositoryCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getKnowledgeTitle() {
        return knowledgeTitle;
    }

    public void setKnowledgeTitle(String knowledgeTitle) {
        this.knowledgeTitle = knowledgeTitle == null ? null : knowledgeTitle.trim();
    }

    public String getKnowledgeDigest() {
        return knowledgeDigest;
    }

    public void setKnowledgeDigest(String knowledgeDigest) {
        this.knowledgeDigest = knowledgeDigest == null ? null : knowledgeDigest.trim();
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode == null ? null : contentCode.trim();
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getAppendNum() {
        return appendNum;
    }

    public void setAppendNum(Integer appendNum) {
        this.appendNum = appendNum;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

	public Byte getCanOpen() {
		return canOpen;
	}

	public void setCanOpen(Byte canOpen) {
		this.canOpen = canOpen;
	}
    
    
}