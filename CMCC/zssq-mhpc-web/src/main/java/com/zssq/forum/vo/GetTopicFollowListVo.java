package com.zssq.forum.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.IntType;

/**
 * 查询跟帖列表输入参数对象
 * @ClassName GetTopicFollowListVo
 * @Description 
 * @author liurong
 * @date 2017年3月18日 下午4:10:47
 * @version 1.0
 * @since JDK 1.7
 */
public class GetTopicFollowListVo {
	
	@NotBlank(message = "{empty.message}")
	private String topicCode;//主帖CODE
	
	private String floorHostCode;//楼主CODE（只看楼主时传入值）

	@IntType(expression=">0")
	private String pageSize;//每页显示行数
	
	@IntType(expression=">=0")
	private String pageNo;//查询起始行数，从0开始
	
	@NotBlank(message = "{empty.message}")
	private String userCode;//用户CODE
	
	@NotBlank(message = "{empty.message}")
	private String token;//Token令牌

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getFloorHostCode() {
		return floorHostCode;
	}

	public void setFloorHostCode(String floorHostCode) {
		this.floorHostCode = floorHostCode;
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
