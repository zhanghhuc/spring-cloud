package com.zssq.rss.po;

import java.io.Serializable;

/**
 * 
 * @ClassName: RssObject  
 * @Description: rss调用各个系统的查询传参对象  
 * @author sry  
 * @date 2017年4月7日  
 *
 */
public class RssQueryPo implements Serializable{

	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = -3396961756832934652L;
	
	/**
	 * 发布者code
	 */
	private String userCode;
	
	/**
	 * 租户code
	 */
	private String tenantCode;
	
	/**
	 * 班组code
	 */
	private String teamCode;
	
	/**
	 * 班组或个人：1，个人；2，班组
	 */
	private int blogDepend;
	
	/**
	 * 需要返回参数个数
	 */
	private int pageSize=20;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public int getBlogDepend() {
		return blogDepend;
	}

	public void setBlogDepend(int blogDepend) {
		this.blogDepend = blogDepend;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
