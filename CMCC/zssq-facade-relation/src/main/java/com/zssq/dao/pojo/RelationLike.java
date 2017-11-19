package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationLike  
 * @Description: 点赞记录  
 * @author ZKZ  
 * @date 2017年4月18日  
 *
 */
public class RelationLike implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id = 0L;
    private String likeCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String subjectCode = "";
    private Byte subjectClass = 0;
    private String userCode = "";

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLikeCode() {
        return likeCode;
    }

    public void setLikeCode(String likeCode) {
        this.likeCode = likeCode == null ? null : likeCode.trim();
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

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public Byte getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(Byte subjectClass) {
        this.subjectClass = subjectClass;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }
}