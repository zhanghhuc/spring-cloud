package com.zssq.dao.pojo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryInfo  
 * @Description: 知识库  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class RepositoryInfo implements Serializable{
        
	private static final long serialVersionUID = 8628749948580673551L;

	private Long id = 0L;

    private String repositoryCode = "";

    private String tenantCode = "";

    private String orgCode = "";

    private Long createTime = 0L;

    private Long modifyTime = 0L;

    private String remark = "";

    private String userCode = "";

    private String repositoryTitle = "";

    private String repositorySummary = "";

    private Byte isDelete = 0;
    
    private String keyWords = "";

    public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepositoryCode() {
        return repositoryCode;
    }

    public void setRepositoryCode(String repositoryCode) {
        this.repositoryCode = repositoryCode == null ? null : repositoryCode.trim();
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

    public String getRepositoryTitle() {
        return repositoryTitle;
    }

    public void setRepositoryTitle(String repositoryTitle) {
        this.repositoryTitle = repositoryTitle == null ? null : repositoryTitle.trim();
    }

    public String getRepositorySummary() {
        return repositorySummary;
    }

    public void setRepositorySummary(String repositorySummary) {
        this.repositorySummary = repositorySummary == null ? null : repositorySummary.trim();
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}