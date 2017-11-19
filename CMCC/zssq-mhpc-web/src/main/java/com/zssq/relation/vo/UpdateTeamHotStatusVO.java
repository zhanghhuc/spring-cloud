package com.zssq.relation.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.zssq.annotation.validation.EnumType;

/**
 * @ClassName UpdateTeamHotStatusVO
 * @Description 更改班组热点状态
 * @author LXW
 * @date 2017年6月12日 上午11:01:37
 * @version 1.0
 * @since JDK 1.7
 */
public class UpdateTeamHotStatusVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * hotCode:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	@NotBlank(message = "{empty.message}")
	private String hotCode;		//热点编号
	@EnumType(allow = { "1", "2" })
	private String actionClass;//操作类型：1.隐藏；2.显示
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

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
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