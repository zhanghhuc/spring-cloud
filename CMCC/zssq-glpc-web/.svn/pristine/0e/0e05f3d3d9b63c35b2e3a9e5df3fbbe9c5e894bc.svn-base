package com.zssq.activity.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.Date;
import com.zssq.annotation.validation.EnumType;

public class CreateActivityTypeOneVo {

	@NotBlank(message = "{empty.message}")
	private String templateCode;
	
	@NotBlank(message = "{empty.message}")
	private String activityName;
	
	@NotBlank(message = "{empty.message}")
	private String introduce;
	
	@NotBlank(message = "{empty.message}")
	private String rule;
	
	@Date(required=false,pattern="yyyy-MM-dd")
	private String preheatTime;
	
	@Date(pattern="yyyy-MM-dd")
	private String startTime;
	
	@Date(pattern="yyyy-MM-dd")
	private String endTime;
	
	@Date(pattern="yyyy-MM-dd")
	private String offlineTime;
	
	@NotBlank(message = "{empty.message}")
	private String organizer;
	
	@EnumType(allow={"0","1"})
	private String enableComment;
	
	@EnumType(allow={"0","1"})
	private String isSubmitExamine;
	
	@EnumType(allow = { "1", "2", "3" })
	private String joinRange;// 参与范围：1-集团 2-省 3-市
	
	@EnumType(allow={"0","1"})
	private String isCascade;
	
	@NotBlank(message = "{empty.message}")
	private String joinRangeCode;
	
	@NotBlank(message = "{empty.message}")
	private String joinRangeName;
	
	private List<Prize> prize ;
	
	private List<Resource> resource ;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getPreheatTime() {
		return preheatTime;
	}

	public void setPreheatTime(String preheatTime) {
		this.preheatTime = preheatTime;
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

	public String getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getEnableComment() {
		return enableComment;
	}

	public void setEnableComment(String enableComment) {
		this.enableComment = enableComment;
	}

	public String getIsSubmitExamine() {
		return isSubmitExamine;
	}

	public void setIsSubmitExamine(String isSubmitExamine) {
		this.isSubmitExamine = isSubmitExamine;
	}

	public String getIsCascade() {
		return isCascade;
	}

	public void setIsCascade(String isCascade) {
		this.isCascade = isCascade;
	}

	public String getJoinRangeCode() {
		return joinRangeCode;
	}

	public void setJoinRangeCode(String joinRangeCode) {
		this.joinRangeCode = joinRangeCode;
	}
	
	public String getJoinRangeName() {
		return joinRangeName;
	}

	public void setJoinRangeName(String joinRangeName) {
		this.joinRangeName = joinRangeName;
	}

	public List<Prize> getPrize() {
		return prize;
	}

	public void setPrize(List<Prize> prize) {
		this.prize = prize;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
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

	public String getJoinRange() {
		return joinRange;
	}

	public void setJoinRange(String joinRange) {
		this.joinRange = joinRange;
	}
	
}
