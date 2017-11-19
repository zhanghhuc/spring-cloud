package com.zssq.vote.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.Date;
import com.zssq.annotation.validation.EnumType;

public class SendVoteByGlVo {


	@NotBlank(message = "{empty.message}")
	private String title;
	
	private String explain;
	
	private List<Option> option;
	
	@EnumType(allow = { "0", "1" })
	private String isMulti;

	@Date(pattern = "yyyy-MM-dd")
	private String endTime;
	
	@EnumType(allow = { "0", "1" })
	private String isEnableComment;

	@EnumType(allow = { "1", "2", "3"})
	private String joinRange;
	
	@NotBlank(message = "{empty.message}")
	private String rangeCode;
	
	@EnumType(allow = { "0", "1" })
	private String isCascade;

	@NotBlank(message = "{empty.message}")
	private String sponsorOrgCode;
	
	@EnumType(allow = { "0", "1" })
	private String isSubmitReview;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public List<Option> getOption() {
		return option;
	}

	public void setOption(List<Option> option) {
		this.option = option;
	}

	public String getIsMulti() {
		return isMulti;
	}

	public void setIsMulti(String isMulti) {
		this.isMulti = isMulti;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsEnableComment() {
		return isEnableComment;
	}

	public void setIsEnableComment(String isEnableComment) {
		this.isEnableComment = isEnableComment;
	}

	public String getJoinRange() {
		return joinRange;
	}

	public void setJoinRange(String joinRange) {
		this.joinRange = joinRange;
	}

	public String getRangeCode() {
		return rangeCode;
	}

	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}

	public String getIsCascade() {
		return isCascade;
	}

	public void setIsCascade(String isCascade) {
		this.isCascade = isCascade;
	}

	public String getSponsorOrgCode() {
		return sponsorOrgCode;
	}

	public void setSponsorOrgCode(String sponsorOrgCode) {
		this.sponsorOrgCode = sponsorOrgCode;
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

	public String getIsSubmitReview() {
		return isSubmitReview;
	}

	public void setIsSubmitReview(String isSubmitReview) {
		this.isSubmitReview = isSubmitReview;
	}
	
}
