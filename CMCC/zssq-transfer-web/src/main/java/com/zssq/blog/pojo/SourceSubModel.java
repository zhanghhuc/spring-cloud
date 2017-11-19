package com.zssq.blog.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: SourceSubModel
 * @Description: 原订阅关系
 * @author ZKZ
 * @date 2017年5月18日
 *
 */
public class SourceSubModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String publishUser; // 被订阅人
	private String subscribeUser; // 订阅人
	private Timestamp createTime; // 订阅时间
	
	private String tenantCode; // 租户编号
	private String orgCode; // 组织编号
	

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	public String getSubscribeUser() {
		return subscribeUser;
	}

	public void setSubscribeUser(String subscribeUser) {
		this.subscribeUser = subscribeUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

}
