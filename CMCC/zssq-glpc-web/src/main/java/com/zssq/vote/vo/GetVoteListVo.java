package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

public class GetVoteListVo {

	private String sponsorCode;
	
	//@EnumType(required=false,allow={"1","2","3","4","5"})
	private String voteStatus;
	
	@EnumType(required=false,allow={"0","1"})
	private String isMultiOption;
	
	private String title;
	
	@EnumType(required=false,allow={"0","1"})
	private String isEnableComment;
	
	@IntType(expression=">=0")
	private String pageNo;
	
	@IntType(expression=">0")
	private String pageSize;

	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public String getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(String voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getIsMultiOption() {
		return isMultiOption;
	}

	public void setIsMultiOption(String isMultiOption) {
		this.isMultiOption = isMultiOption;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsEnableComment() {
		return isEnableComment;
	}

	public void setIsEnableComment(String isEnableComment) {
		this.isEnableComment = isEnableComment;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
