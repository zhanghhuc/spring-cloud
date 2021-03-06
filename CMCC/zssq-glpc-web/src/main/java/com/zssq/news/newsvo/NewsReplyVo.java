package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author SharlaCheung
 * @ClassName: NewsInfoContentVo
 * @Description: 评论回复参数类
 * @date 2017年04月02日
 */
public class NewsReplyVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String token;
	@NotBlank(message = "{empty.message}")
	private String newsCommentCode;
	@IntType(expression = ">=0")
	private String id;
	@IntType(expression = ">0")
	private Integer pageSize;
	@IntType(expression = ">=0")
	private Integer PageNo;

	private String newsCode;
	private String orgCode;
	private String userCode;

	
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

	public String getNewsCommentCode() {
		return newsCommentCode;
	}

	public void setNewsCommentCode(String newsCommentCode) {
		this.newsCommentCode = newsCommentCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(String newsCode) {
		this.newsCode = newsCode;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return PageNo;
	}

	public void setPageNo(Integer pageNo) {
		PageNo = pageNo;
	}
}
