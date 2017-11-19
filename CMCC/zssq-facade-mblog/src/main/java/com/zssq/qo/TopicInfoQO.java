package com.zssq.qo;

import java.io.Serializable;

/**
 * 
    * @ClassName: TopicInfoQO  
    * @Description: 话题信息  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public class TopicInfoQO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 每页条数 **/
	private Integer pageSize = null;
	/** 页码 **/
	private Integer pageNo = null;
	/** 分割数据 **/
	private Long lineTime;
	/** 话题名字 **/
	private String topicName = "";
	/** 组织CODE **/
	private String orgCode = "";
	/** 租户 **/
	private String tenantCode = "";
	
	public Integer getLimitStart() {
		if(null != pageNo && null != pageSize && pageSize > 0){
			return pageNo*pageSize;
		}
		return null;
	}
	public Integer getLimitSize() {
		if(null != pageSize && pageSize > 0){
			return pageSize;
		}
		return null;
		
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Long getLineTime() {
		return lineTime;
	}
	public void setLineTime(Long lineTime) {
		this.lineTime = lineTime;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
}
