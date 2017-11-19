package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: GetBlogInfoVo  
 * @Description: 获取博客详情  
 * @author sry  
 * @date 2017年4月15日  
 *
 */
public class GetBlogInfoVo implements Serializable{
	
	    
	/**  
	 * @Fields serialVersionUID : TODO
	 */  
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String blogCode;
	
	@NotBlank(message = "{empty.message}")
	private String menuCode;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getBlogCode() {
		return blogCode;
	}

	public void setBlogCode(String blogCode) {
		this.blogCode = blogCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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
