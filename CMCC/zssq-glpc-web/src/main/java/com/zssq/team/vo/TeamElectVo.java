package com.zssq.team.vo;

import org.hibernate.validator.constraints.NotBlank;

public class TeamElectVo {

	/** 评选标题 */
	@NotBlank(message = "{empty.message}")
	private String electTitle;
	
	/** 评选说明 */
	@NotBlank(message = "{empty.message}")
	private String electDetail;
	
	/** 评选开始时间 */
	@NotBlank(message = "{empty.message}")
	private String electStartTime;
	
	/** 评选结束时间 */
	@NotBlank(message = "{empty.message}")
	private String electEndTime;
	
	/** 用户编码 */
	private String userCode;

	public String getElectTitle() {
		return electTitle;
	}

	public void setElectTitle(String electTitle) {
		this.electTitle = electTitle;
	}

	public String getElectDetail() {
		return electDetail;
	}

	public void setElectDetail(String electDetail) {
		this.electDetail = electDetail;
	}

	public String getElectStartTime() {
		return electStartTime;
	}

	public void setElectStartTime(String electStartTime) {
		this.electStartTime = electStartTime;
	}

	public String getElectEndTime() {
		return electEndTime;
	}

	public void setElectEndTime(String electEndTime) {
		this.electEndTime = electEndTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
