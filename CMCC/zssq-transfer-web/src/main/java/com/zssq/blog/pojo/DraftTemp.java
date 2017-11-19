package com.zssq.blog.pojo;

import java.io.Serializable;

/**
 * 
 * @ClassName: DraftTemp
 * @Description: 草稿临时表
 * @author ZKZ
 * @date 2017年5月22日
 *
 */
public class DraftTemp implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer oldId = 0;
	private String draftCode = "";
	private String tenantCode = "";
	private String orgCode = "";
	private Long createTime = 0L;
	private Long modifyTime = 0L;
	

	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

	public String getDraftCode() {
		return draftCode;
	}

	public void setDraftCode(String draftCode) {
		this.draftCode = draftCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

}