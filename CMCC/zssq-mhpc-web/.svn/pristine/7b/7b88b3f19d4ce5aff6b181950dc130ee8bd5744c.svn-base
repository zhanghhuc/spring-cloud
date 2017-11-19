package com.zssq.vote.vo;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 门户页面获取投票列表输入参数对象
 * @ClassName GetGatewayVoteListVo
 * @Description 
 * @author liurong
 * @date 2017年4月5日 下午4:50:03
 * @version 1.0
 * @since JDK 1.7
 */
public class GetGatewayVoteListVo {

	@NotBlank(message = "{empty.message}")
	private String gatewayCode;
	
	@NotBlank(message = "{empty.message}")
	private String pageSize;
	
	@NotBlank(message = "{empty.message}")
	private String userCode;
	
	@NotBlank(message = "{empty.message}")
	private String token;

	public String getGatewayCode() {
		return gatewayCode;
	}

	public void setGatewayCode(String gatewayCode) {
		this.gatewayCode = gatewayCode;
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
