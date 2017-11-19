package com.zssq.activity.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 
 * @ClassName: GetPortalActivityListVo  
 * @Description: 获取门户页面活动列表
 * @author liuzhijie  
 * @date 2017年4月24日  
 *
 */
public class GetPortalActivityListVo implements Serializable {
 
	private static final long serialVersionUID = 890833279669235994L;

	@NotBlank(message = "{empty.message}")
	private String protalCode;//门户CODE（组织机构CODE）
	
	@IntType(required = false,expression = ">0")
	private String id;//评论数据ID，点加载更多时，传入最后一条数据的id
	
	@IntType(expression=">0")
	private String pageSize;//每页显示数据条数
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//当前登录用户唯一标识CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌

	public String getProtalCode() {
		return protalCode;
	}

	public void setProtalCode(String protalCode) {
		this.protalCode = protalCode;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
