package com.zssq.blog.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: MySQLBlogClass
 * @Description: 博客分类
 * @author ZKZ
 * @date 2017年5月19日
 *
 */
public class MySQLBlogClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id = 0L;
	private String classCode = "";
	private String tenantCode = "";
	private String orgCode = "";
	private Long createTime = 0L;
	private Long modifyTime = 0L;
	private String remark = "";
	private String className = "";
	private String userCode = "";
	private Byte classDepend = 0;
	private String teamCode = "";
	private Integer classBlogNum = 0;
	private Integer oldId = 0;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Byte getClassDepend() {
		return classDepend;
	}

	public void setClassDepend(Byte classDepend) {
		this.classDepend = classDepend;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public Integer getClassBlogNum() {
		return classBlogNum;
	}

	public void setClassBlogNum(Integer classBlogNum) {
		this.classBlogNum = classBlogNum;
	}

	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

}
