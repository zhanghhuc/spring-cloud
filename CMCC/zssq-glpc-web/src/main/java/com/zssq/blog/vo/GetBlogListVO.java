package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;
import com.zssq.annotation.validation.LongType;

/**
 * 
 * @ClassName: GetUserBlogListVO
 * @Description: 查询博客列表参数
 * @author ZKZ
 * @date 2017年4月11日
 *
 */
public class GetBlogListVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EnumType(required=true ,allow={"0","1"})
	private Byte blogIsShield;//是否屏蔽：0.否；1.是；
	@NotBlank(message = "{empty.message}")
	private String menuCode;// 菜单编号
	@IntType(expression = ">0")
	private String pageSize; // 每页条数
	@IntType(expression = ">=0")
	private String pageNo; // 页码
	@LongType(expression = ">0")
	private String queryTime; // 首次查询时间
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN
	public Byte getBlogIsShield() {
		return blogIsShield;
	}
	public void setBlogIsShield(Byte blogIsShield) {
		this.blogIsShield = blogIsShield;
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
	
	
	
}
