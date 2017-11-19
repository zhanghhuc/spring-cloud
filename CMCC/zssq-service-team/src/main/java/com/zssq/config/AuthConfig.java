package com.zssq.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

@Service("authConfig")
@Scope("singleton")
@DisconfFile(filename = "auth.properties")
public class AuthConfig {

	/** 班组默认头像 */
	private String teamIcon;
	
	/** 用户默认头像 */
	private String userHead;
	
	
	@DisconfFileItem(name = "team_icon", associateField = "teamIcon")	
	public String getTeamIcon() {
		return teamIcon;
	}

	public void setTeamIcon(String teamIcon) {
		this.teamIcon = teamIcon;
	}

	@DisconfFileItem(name = "user_head", associateField = "userHead")
	public String getUserHead() {
		return userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}

}
