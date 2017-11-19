package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * @ClassName UpdateQualitySeqVO
 * @Description 精华上移下移
 * @author LXW
 * @date 2017年6月12日 上午11:41:21
 * @version 1.0
 * @since JDK 1.7
 */
public class UpdateQualitySeqVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String qualityCode;// 精华编号
	@IntType(expression = ">0")
	private String qualitySeqNum;// 排序
	@EnumType(allow = { "1", "2" })
	private String actionClass;// 操作类型：1.上移；2.下移
	private String subjectTitle = "";// 标题
	@EnumType(allow = { "1", "2" }, required = false)
	private String subjectClass; // 应用类型：1.微博；2.博客；
	@LongType(expression = ">0", required = false)
	private String beginTime;
	@LongType(expression = ">0", required = false)
	private String endTime;
	@NotBlank(message = "{empty.message}")
	private String menuCode;// 菜单编号
	@LongType(expression = ">0")
	private String queryTime; // 首次查询时间
	@NotBlank(message = "{empty.message}")
	private String userCode;// 当前登录用户的userCode
	@NotBlank(message = "{empty.message}")
	private String token;// TOKEN
	

	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}

	public String getQualitySeqNum() {
		return qualitySeqNum;
	}

	public void setQualitySeqNum(String qualitySeqNum) {
		this.qualitySeqNum = qualitySeqNum;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
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
