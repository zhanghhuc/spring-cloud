package com.zssq.news.newsvo;

import com.zssq.annotation.validation.IntType;
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
public class NewsInfoListVO implements Serializable {
	
	@NotBlank(message = "{empty.message}")
	private String userCode;

	@NotBlank(message = "{empty.message}")
	private String orgCode;

	@IntType(expression = ">0")
	private String pageSize;
	
	@IntType(expression = ">=0")
	private String PageNo;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return PageNo;
	}

	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
