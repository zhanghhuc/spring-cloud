package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.Date;
import com.zssq.annotation.validation.EnumType;

/**
 * 
 * @ClassName: ExaminingActivityVo  
 * @Description: 审核活动（通过/驳回）
 * @author liuzhijie  
 * @date 2017年4月24日  
 *
 */
public class ExaminingActivityVo implements Serializable {

	private static final long serialVersionUID = 1281198998411380587L;

	@NotBlank(message = "{empty.message}")
	private String activityCode;//活动CODE
	
	@EnumType(required=true ,allow={"1","2"})
	private String examineResult;//审核结果：1-通过  2-驳回
	
	@Date(required = false, pattern = "yyyy-MM-dd")
	private String preheatTime;//活动预热时间：yyyy-MM-dd
	
	@Date(required = false, pattern = "yyyy-MM-dd")
	private String startTime;//活动开始时间：yyyy-MM-dd
	
	@Date(required = false, pattern = "yyyy-MM-dd")
	private String endTime;//活动结束时间：yyyy-MM-dd
	
	@Date(required = false, pattern = "yyyy-MM-dd")
	private String offlineTime;//活动下线时间：yyyy-MM-dd
	
	private String remark;//批注
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌
	
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getExamineResult() {
		return examineResult;
	}
	public void setExamineResult(String examineResult) {
		this.examineResult = examineResult;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
