package com.zssq.news.newsvo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: NewsInfoListVO
 * @Description: 新闻列表查询类
 * @author SharlaCheung
 * @date 2017年4月10日
 *
 */
public class NewsArchiveVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String userCode;
	@NotBlank(message = "{empty.message}")
	private String newsCodes;
	@NotBlank(message = "{empty.message}")
	private String archiveTitle;

	private String userName;
	private String orgCode;
	private String startTime;
	private String endTime;
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getNewsCodes() {
		return newsCodes;
	}

	public void setNewsCodes(String newsCodes) {
		this.newsCodes = newsCodes;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getArchiveTitle() {
		return archiveTitle;
	}

	public void setArchiveTitle(String archiveTitle) {
		this.archiveTitle = archiveTitle;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
