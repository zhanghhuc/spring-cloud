package com.zssq.blog.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @ClassName: GetTeamDraftInfoVO
 * @Description: 查询班组草稿详情参数
 * @author ZKZ
 * @date 2017年3月23日
 *
 */
public class GetTeamDraftInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "{empty.message}")
	private String draftCode; // 草稿编号
	@NotBlank(message = "{empty.message}")
	private String teamCode; // 班组编号
	@NotBlank(message = "{empty.message}")
	private String userCode; // 当前登录用户编号
	@NotBlank(message = "{empty.message}")
	private String token; // TOKEN

	
	public String getDraftCode() {
		return draftCode;
	}

	public void setDraftCode(String draftCode) {
		this.draftCode = draftCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
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
