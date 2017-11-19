package com.zssq.mblog.pojo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("unused")
public class TransferTaskVo{


	private String taskName;
	/** 1：迁移原创微博，2：迁移转发微博，3：迁移评论信息，4：迁移at信息，5：迁移话题信息，6：迁移订阅  7：迁移转发历程信息**/
	private Byte taskType;
	/** 1:未开始，2：进行中，3：完成，4：失败 **/
	private Byte taskStatus;
	private Integer pageNo = 0;
	private Integer totalPage = 0;
	private Integer totalCount = 0;
	private Long createTime = new Date().getTime();
	private Long startTime = 0L;
	private Long endTime = 0L;
	private Long failTime = 0L;
	 
	
	private String taskStatusView;
	
	public String getTaskStatusView(){
		String result = "";
		switch(taskStatus){
			case TransferTaskConstants.TASK_STATUS_NEW:{
				result = "新任务";
				break;
			}
			case TransferTaskConstants.TASK_STATUS_RUNING:{
				result = "进行中";
				break;
			}
			case TransferTaskConstants.TASK_STATUS_END:{
				result = "成功";
				break;
			}
			case TransferTaskConstants.TASK_STATUS_FAIL:{
				result = "失败";
				break;
			}
		}
		return result;
	}
	public void setTaskStatusView(String taskStatusView) {
		this.taskStatusView = taskStatusView;
	}
	
	
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getFailTime() {
		return failTime;
	}
	public void setFailTime(Long failTime) {
		this.failTime = failTime;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public Byte getTaskType() {
		return taskType;
	}

	public void setTaskType(Byte taskType) {
		this.taskType = taskType;
	}

	public Byte getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Byte taskStatus) {
		this.taskStatus = taskStatus;
	}

}
