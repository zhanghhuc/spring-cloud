package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 查询主帖列表输入参数对象
 * @ClassName GetTopicList
 * @Description 
 * @author zhangguoqing
 * @date 2017年3月17日 下午5:52:19
 * @version 1.0
 * @since JDK 1.7
 */
public class GetTopicListVo {

	@NotBlank(message = "{empty.message}")
	private String belongCode;//论坛所属CODE（这里传入班组CODE即可）
	
	@IntType(expression=">0")
	private String pageSize;//每页显示行数
	
	@IntType(expression=">=0")
	private String pageNo;//查询起始行数，从0开始
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌
	
	public String getBelongCode() {
		return belongCode;
	}
	public void setBelongCode(String belongCode) {
		this.belongCode = belongCode;
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
