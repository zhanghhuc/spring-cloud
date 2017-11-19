package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;
import com.zssq.annotation.validation.IntType;
/**
 * 获取我/班组/门户发起的投票列表 输入参与对象
 * @ClassName GetPublishVoteListVo
 * @Description 
 * @author liurong
 * @date 2017年4月5日 上午10:41:42
 * @version 1.0
 * @since JDK 1.7
 */
public class GetPublishVoteListVo {

	@EnumType(allow={"1","3"})
	private String sponsorType;
	
	private String sponsorCode;
	
	private String sponsorOrgCode;

	@EnumType(required = false, allow = { "4", "5" })
	private String voteStatus;

	@IntType(required = false, expression = ">0")
	private String id;
	
	@IntType(expression = ">0")
	private String pageSize;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getSponsorType() {
		return sponsorType;
	}

	public void setSponsorType(String sponsorType) {
		this.sponsorType = sponsorType;
	}

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public String getSponsorOrgCode() {
		return sponsorOrgCode;
	}

	public void setSponsorOrgCode(String sponsorOrgCode) {
		this.sponsorOrgCode = sponsorOrgCode;
	}

	public String getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(String voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
}
