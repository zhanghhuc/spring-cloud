package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationQuality  
 * @Description: 精华  
 * @author ZKZ  
 * @date 2017年4月18日  
 *
 */
public class RelationQuality implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id = 0L;
    private String qualityCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private String teamCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String subjectCode = "";
    private Byte subjectClass = 0;
    private Byte qualityDepend = 0;
    private Integer qualitySeqNum = 0;
    private Byte qualityIsTop = 0;
    private Long qualityTopTime = 0L;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualityCode() {
        return qualityCode;
    }

    public void setQualityCode(String qualityCode) {
        this.qualityCode = qualityCode == null ? null : qualityCode.trim();
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


    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
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

    public Byte getQualityDepend() {
        return qualityDepend;
    }

    public void setQualityDepend(Byte qualityDepend) {
        this.qualityDepend = qualityDepend;
    }

    public Integer getQualitySeqNum() {
        return qualitySeqNum;
    }

    public void setQualitySeqNum(Integer qualitySeqNum) {
        this.qualitySeqNum = qualitySeqNum;
    }

    public Byte getQualityIsTop() {
        return qualityIsTop;
    }

    public void setQualityIsTop(Byte qualityIsTop) {
        this.qualityIsTop = qualityIsTop;
    }

    public Long getQualityTopTime() {
        return qualityTopTime;
    }

    public void setQualityTopTime(Long qualityTopTime) {
        this.qualityTopTime = qualityTopTime;
    }
}