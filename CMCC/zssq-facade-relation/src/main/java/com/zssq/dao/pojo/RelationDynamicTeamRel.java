package com.zssq.dao.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: RelationDynamicTeamRel  
 * @Description: 动态班组关系  
 * @author ZKZ  
 * @date 2017年4月18日  
 *
 */
public class RelationDynamicTeamRel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id = 0L;
    private String relCode = "";
    private String tenantCode = "";
    private String orgCode = "";
    private Long createTime = 0L;
    private Long modifyTime = 0L;
    private String remark = "";
    private String dynamicCode = "";
    private String teamCode = "";
    private Byte teamIsExcellent = 0;
    private Byte teamIsNoOne = 0;
    private Byte teamIsDissolve = 0;
    private Byte relIsHomeShow = 0;
    private Byte relIsQuality = 0;
    private Byte relIsRecommend = 0;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelCode() {
        return relCode;
    }

    public void setRelCode(String relCode) {
        this.relCode = relCode == null ? null : relCode.trim();
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

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode == null ? null : dynamicCode.trim();
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    public Byte getTeamIsExcellent() {
        return teamIsExcellent;
    }

    public void setTeamIsExcellent(Byte teamIsExcellent) {
        this.teamIsExcellent = teamIsExcellent;
    }

    public Byte getTeamIsNoOne() {
		return teamIsNoOne;
	}

	public void setTeamIsNoOne(Byte teamIsNoOne) {
		this.teamIsNoOne = teamIsNoOne;
	}

	public Byte getTeamIsDissolve() {
        return teamIsDissolve;
    }

    public void setTeamIsDissolve(Byte teamIsDissolve) {
        this.teamIsDissolve = teamIsDissolve;
    }

    public Byte getRelIsHomeShow() {
        return relIsHomeShow;
    }

    public void setRelIsHomeShow(Byte relIsHomeShow) {
        this.relIsHomeShow = relIsHomeShow;
    }

    public Byte getRelIsQuality() {
        return relIsQuality;
    }

    public void setRelIsQuality(Byte relIsQuality) {
        this.relIsQuality = relIsQuality;
    }

    public Byte getRelIsRecommend() {
        return relIsRecommend;
    }

    public void setRelIsRecommend(Byte relIsRecommend) {
        this.relIsRecommend = relIsRecommend;
    }
}