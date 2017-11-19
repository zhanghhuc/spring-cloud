package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: SaveUserDraftVO
 * @Description: 保存个人草稿参数
 * @author ZKZ
 * @date 2017年3月23日
 *
 */
public class SaveUserDraftVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String draftCode = ""; // 草稿编号
	@Length(min=1, max=60)
	private String draftTitle; // 标题
	@NotBlank(message = "{empty.message}")
	private String classCode; // 分类编号
	private String draftTags = ""; // 标签
	private String draftPlanPublishTime = "0"; // 定时发布时间
	private String draftContentInfo = ""; // 正文内容
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	
	public String getDraftCode() {
		return draftCode;
	}

	public void setDraftCode(String draftCode) {
		this.draftCode = draftCode;
	}

	public String getDraftTitle() {
		return draftTitle;
	}

	public void setDraftTitle(String draftTitle) {
		this.draftTitle = draftTitle;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getDraftTags() {
		return draftTags;
	}

	public void setDraftTags(String draftTags) {
		this.draftTags = draftTags;
	}

	public String getDraftPlanPublishTime() {
		return draftPlanPublishTime;
	}

	public void setDraftPlanPublishTime(String draftPlanPublishTime) {
		this.draftPlanPublishTime = draftPlanPublishTime;
	}

	public String getDraftContentInfo() {
		return draftContentInfo;
	}

	public void setDraftContentInfo(String draftContentInfo) {
		this.draftContentInfo = draftContentInfo;
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
