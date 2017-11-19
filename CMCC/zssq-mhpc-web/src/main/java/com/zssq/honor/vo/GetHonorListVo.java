package com.zssq.honor.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;

/**
 * @ClassName GetHonorListVo
 * @Description 获取荣誉列表输入对象
 * @author LiuYunLong
 * @date 2017年3月21日 下午3:22:56
 * @version 1.0
 * @since JDK 1.7
 */
public class GetHonorListVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String honorName;  //荣誉名称

	@EnumType(required = false,allow = {"1","2"})
	private String honorType;  //荣誉类型
	
	@EnumType(required = true,allow = {"0","1"})
	private String isPageFlag;  //是否需要分页
	
	@IntType(required = false,expression = ">0")
	private String pageSize;  //每页显示行数
	
	@IntType(required = false,expression = ">=0")
	private String pageNo;  //查询起始页码，从0开始

	@NotBlank(message = "{empty.message}")
	private String userCode;  //用户CODE

	@NotBlank(message = "{empty.message}")
	private String token;  //Token令牌

	public String getIsPageFlag() {
		return isPageFlag;
	}

	public void setIsPageFlag(String isPageFlag) {
		this.isPageFlag = isPageFlag;
	}

	public String getHonorName() {
		return honorName;
	}

	public String getHonorType() {
		return honorType;
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

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}

	public void setHonorType(String honorType) {
		this.honorType = honorType;
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
