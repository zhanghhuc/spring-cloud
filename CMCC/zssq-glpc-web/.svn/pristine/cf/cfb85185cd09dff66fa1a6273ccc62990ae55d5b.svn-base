package com.zssq.news.newsvo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 
 * @ClassName: NewsInfoListVO
 * @Description: 新闻列表查询类
 * @author SharlaCheung
 * @date 2017年4月10日
 *
 */
public class NewsInfoHistoryListVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	@NotBlank(message = "{empty.message}")
	private String newsCode;


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getNewsCode() {
		return newsCode;
	}

	public void setNewsCode(String newsCode) {
		this.newsCode = newsCode;
	}
}
