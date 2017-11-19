package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName UpdateTeamHotStatusVO
 * @Description 修改班组热点状态
 * @author LXW
 * @date 2017年6月12日 下午3:12:56
 * @version 1.0
 * @since JDK 1.7
 */
public class UpdateTeamHotStatusVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "{empty.message}")
	private String hotCode;		//热点编号
	@NotBlank(message = "{empty.message}")
	private String teamCode;	//班组编号
	@NotBlank(message = "{empty.message}")
	private String userCode;	//当前登录用户的userCode
	@NotBlank(message = "{empty.message}")
	private String token;		//TOKEN

	public String getHotCode() {
		return hotCode;
	}

	public void setHotCode(String hotCode) {
		this.hotCode = hotCode;
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
