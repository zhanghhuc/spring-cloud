package com.zssq.vote.vo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import com.zssq.annotation.validation.Date;
import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName AdvanceFinishVoteVo
 * @Description 修改投票信息 输入对象
 * @author LiuYunLong
 * @date 2017年4月6日 上午11:56:00
 * @version 1.0
 * @since JDK 1.7
 */
public class UpdateVoteInfoVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String voteInfoCode;
	
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
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;
	
	@EnumType(allow = { "0", "1" })
	private String isSubmitReview;

	public String getVoteInfoCode() {
		return voteInfoCode;
	}

	public String getTitle() {
		return title;
	}

	public String getExplain() {
		return explain;
	}

	public List<Option> getOption() {
		return option;
	}

	public String getIsMulti() {
		return isMulti;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getIsEnableComment() {
		return isEnableComment;
	}

	public String getJoinRange() {
		return joinRange;
	}

	public String getRangeCode() {
		return rangeCode;
	}

	public String getIsCascade() {
		return isCascade;
	}

	public String getSponsorOrgCode() {
		return sponsorOrgCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setVoteInfoCode(String voteInfoCode) {
		this.voteInfoCode = voteInfoCode;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public void setOption(List<Option> option) {
		this.option = option;
	}

	public void setIsMulti(String isMulti) {
		this.isMulti = isMulti;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setIsEnableComment(String isEnableComment) {
		this.isEnableComment = isEnableComment;
	}

	public void setJoinRange(String joinRange) {
		this.joinRange = joinRange;
	}

	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}

	public void setIsCascade(String isCascade) {
		this.isCascade = isCascade;
	}

	public void setSponsorOrgCode(String sponsorOrgCode) {
		this.sponsorOrgCode = sponsorOrgCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
