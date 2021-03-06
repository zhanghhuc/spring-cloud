package com.zssq.dao.pojo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledgeAppend  
 * @Description: 知识追加  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class RepositoryKnowledgeAppend implements Serializable{
        
	private static final long serialVersionUID = 8764380080113857696L;

	private Long id =  0L;

    private String appendCode = "";

    private String tenantCode = "";

    private String orgCode = "";

    private Long createTime = 0L;

    private Long modifyTime = 0L;

    private String remark = "";

    private String knowledgeCode = "";

    private String userCode = "";

    private Byte isSelfAppend = 0;

    private Integer praiseNum = 0;
    
    private String appendDigest = "";
    
    private String appendContent = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppendCode() {
        return appendCode;
    }

    public void setAppendCode(String appendCode) {
        this.appendCode = appendCode == null ? null : appendCode.trim();
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

    public String getKnowledgeCode() {
        return knowledgeCode;
    }

    public void setKnowledgeCode(String knowledgeCode) {
        this.knowledgeCode = knowledgeCode == null ? null : knowledgeCode.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Byte getIsSelfAppend() {
        return isSelfAppend;
    }

    public void setIsSelfAppend(Byte isSelfAppend) {
        this.isSelfAppend = isSelfAppend;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getAppendContent() {
        return appendContent;
    }

    public void setAppendContent(String appendContent) {
        this.appendContent = appendContent == null ? null : appendContent.trim();
    }

	public String getAppendDigest() {
		return appendDigest;
	}

	public void setAppendDigest(String appendDigest) {
		this.appendDigest = appendDigest;
	}
    
    
}