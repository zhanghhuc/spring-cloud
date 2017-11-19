package com.zssq.honor.vo;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetAwardWallVo
 * @Description 获取荣誉墙输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:19:22
 * @version 1.0
 * @since JDK 1.7
 */
public class GetAwardWallVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String honoreeCode;  //领奖对象CODE
	
	@IntType(expression = ">0")
	private String pageSize;  //每页显示行数
	
	@IntType(required = false,expression = ">0")
	private String id;  //加载更多时最小id
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHonoreeCode() {
		return honoreeCode;
	}

	public String getPageSize() {
		return pageSize;
	}


	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setHonoreeCode(String honoreeCode) {
		this.honoreeCode = honoreeCode;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
