package com.zssq.dao.pojo;

import java.io.Serializable;
/**
 * 
 * @ClassName: RepositoryKnowledgeContent  
 * @Description: 知识正文  
 * @author sry  
 * @date 2017年6月19日  
 *
 */
public class RepositoryKnowledgeContent implements Serializable{
        
	private static final long serialVersionUID = -4602146332512796724L;

	private Long id = 0L;

    private String contentCode = "";

    private String tenantCode = "";

    private String orgCode = "";

    private Long createTime = 0L;

    private Long modifyTime = 0L;

    private String remark = "";

    private Byte isDelete = 0;

    private String contentInfo = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode == null ? null : contentCode.trim();
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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo == null ? null : contentInfo.trim();
    }
}