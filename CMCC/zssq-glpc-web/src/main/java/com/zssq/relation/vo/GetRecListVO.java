package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * @ClassName GetRecListVo
 * @Description 查询推荐列表
 * @author LXW
 * @date 2017年6月12日 上午11:36:56
 * @version 1.0
 * @since JDK 1.7
 */
public class GetRecListVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@EnumType(allow = { "1", "2", "3", "4" }, required = false)
	private String recStatus;// 1.未处理；2.展示中；3.已忽略；4.已取消
	private String subjectTitle = "";// 标题
	@EnumType(allow = { "1", "2" }, required = false)
	private String subjectClass;// 1.微博；2.博客；
	@LongType(expression = ">0", required = false)
	private String beginTime;// 推荐开始时间
	@LongType(expression = ">0", required = false)
	private String endTime;// 推荐结束时间
	@NotBlank(message = "{empty.message}")
	private String menuCode;// 菜单编号
	@IntType(expression = ">0")
	private String pageSize;
	@IntType(expression = ">=0")
	private String pageNo;
	@LongType(expression = ">0")
	private String queryTime;// 首次查询时间
	@NotBlank(message = "{empty.message}")
	private String userCode; // 用户CODE
	@NotBlank(message = "{empty.message}")
	private String token; // token

	
	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
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
