package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 
 * @ClassName: NewsCommentListVO
 * @Description: 新闻评论列表查询类
 * @author SharlaCheung
 * @date 2017年4月10日
 *
 */
public class NewsCommentListVO implements Serializable {
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	private String orgCode;

	@NotBlank(message = "{empty.message}")
	private String newsCode;

	@IntType(expression = ">0")
	private Integer pageSize;
	
	@IntType(expression = ">=0")
	private Integer PageNo;

	private String commentCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(String newsCode) {
		this.newsCode = newsCode;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}
}
