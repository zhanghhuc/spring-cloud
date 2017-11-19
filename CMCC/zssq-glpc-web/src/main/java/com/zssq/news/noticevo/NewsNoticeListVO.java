package com.zssq.news.noticevo;

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
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String userCode;
	@IntType(expression = ">0")
	private String pageSize;
	@IntType(expression = ">=0")
	private String PageNo;
	@IntType(expression = ">=0")
	private String selfFlag;

	private String noticeStatus;
	private String noticeTitle;
	private String startTime;
	private String endTime;
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return PageNo;
	}

	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
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

	public String getSelfFlag() {
		return selfFlag;
	}

	public void setSelfFlag(String selfFlag) {
		this.selfFlag = selfFlag;
	}
}
