package com.zssq.job.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;




public class JobVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String jobName;
	@NotBlank(message = "{empty.message}")
	private String jobClass;
	@NotBlank(message = "{empty.message}")
	private String shardingTotalCount;
	@NotBlank(message = "{empty.message}")
	private String cron;
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getShardingTotalCount() {
		return shardingTotalCount;
	}
	public void setShardingTotalCount(String shardingTotalCount) {
		this.shardingTotalCount = shardingTotalCount;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	
	

	
	
}