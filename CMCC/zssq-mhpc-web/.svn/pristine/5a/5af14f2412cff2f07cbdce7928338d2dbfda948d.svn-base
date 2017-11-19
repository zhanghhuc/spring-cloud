package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetAwardListVo
 * @Description 查询荣誉颁发记录列表输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:15:59
 * @version 1.0
 * @since JDK 1.7
 */
public class GetAwardListVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{empty.message}")
	private String awarderCode;  //颁发人CODE
	
	@EnumType(required = false,allow = {"0","1"})
	private String isRevoked;  //是否被撤销
	
	@IntType(expression = ">0")
	private String pageSize;  //每页显示行数
	
	@IntType(expression = ">=0")
	private String pageNo;  //查询起始页码，从0开始
	
	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getAwarderCode() {
		return awarderCode;
	}

	public String getIsRevoked() {
		return isRevoked;
	}

	public String getPageSize() {
		return pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getToken() {
		return token;
	}

	public void setAwarderCode(String awarderCode) {
		this.awarderCode = awarderCode;
	}

	public void setIsRevoked(String isRevoked) {
		this.isRevoked = isRevoked;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
