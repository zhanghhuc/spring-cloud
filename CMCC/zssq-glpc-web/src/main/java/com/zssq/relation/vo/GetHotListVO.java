package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;
/**
 * 
 * @ClassName: GetHotListVO  
 * @Description: 热点列表Vo  
 * @author sry  
 * @date 2017年4月17日  
 *
 */
public class GetHotListVO implements Serializable {
	
	
	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = 1L;
	@EnumType(required=false ,allow={"1","2"})
	private String hotStatus;	//热点状态：1.展示中；2.已隐藏
	
	private String subjectTitle;//标题内容
	@EnumType(required=false ,allow={"1","2","3","4","5"})
	private String subjectClass;//应用类型：1.微博；2.博客；3.投票；4.活动；5.新闻公告；
	@LongType(required=false ,expression = ">0")
	private String beginTime;	//发布开始时间
	@LongType(required=false ,expression = ">0")
	private String endTime;		//发布结束时间
	
	@NotBlank(message = "{empty.message}")
	private String menuCode;	//菜单编号
	
	@IntType(expression = ">0")
	private String pageSize;	//每页条数
	
	@IntType(expression = ">=0")
	private String pageNo;		//页码，从0开始
	
	@LongType(expression = ">0")
	private String queryTime;	//首次查询时间，前端生成传入
	
	@NotBlank(message = "{empty.message}")
	private String userCode;	//当前登录用户的userCode
	
	@NotBlank(message = "{empty.message}")
	private String token;		//TOKEN

	public String getHotStatus() {
		return hotStatus;
	}

	public void setHotStatus(String hotStatus) {
		this.hotStatus = hotStatus;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public String getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
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