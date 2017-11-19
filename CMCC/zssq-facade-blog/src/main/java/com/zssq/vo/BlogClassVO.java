package com.zssq.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName: BlogClassVO
 * @Description: 博客分类参数
 * @author ZKZ
 * @date 2017年3月21日
 *
 */
public class BlogClassVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Byte classDepend; // 从属关系：1.个人；2.班组
	private String classCode; // 分类编号
	private String userCode; // 人员编号
	private String teamCode; // 班组编号
	private Integer classBlogNum; // 分类下博客数量
	private Long modifyTime; // 修改时间
	
	private String tenantCode; // 租户编号
	private String orgCode; // 组织编号
	

	public Byte getClassDepend() {
		return classDepend;
	}

	public void setClassDepend(Byte classDepend) {
		this.classDepend = classDepend;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
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
