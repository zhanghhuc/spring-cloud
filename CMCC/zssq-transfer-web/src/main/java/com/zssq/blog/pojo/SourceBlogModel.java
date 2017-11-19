package com.zssq.blog.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: SourceBlogModel
 * @Description: 博客
 * @author ZKZ
 * @date 2017年5月22日
 *
 */
public class SourceBlogModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer blogId;
	private String userCode;
	private Timestamp createTime;
	private Timestamp updatetime;
	private String title;
	private String digest;
	private String classCode;
	private String blogLogo;
	private Integer viewNum;
	private String tenantCode;
	private String teamCode;
	private String cityCode;
	private String provinceCode;
	

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getBlogLogo() {
		return blogLogo;
	}

	public void setBlogLogo(String blogLogo) {
		this.blogLogo = blogLogo;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

}
