package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 
 * @ClassName: NewsNoticeListVO
 * @Description: 公告列表查询类
 * @author SharlaCheung
 * @date 2017年4月14日
 *
 */
public class NewsNoticeListVO implements Serializable {
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	@NotBlank(message = "{empty.message}")
	private String orgCode;

	@IntType(expression = ">0")
	private Integer pageSize;
	
	@IntType(expression = ">=0")
	private Integer PageNo;

	private int noticeStatus;

	private String infoTitle;

	private long startTime;

	private long endTime;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return PageNo;
	}

	public void setPageNo(Integer pageNo) {
		PageNo = pageNo;
	}

	public int getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(int noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
