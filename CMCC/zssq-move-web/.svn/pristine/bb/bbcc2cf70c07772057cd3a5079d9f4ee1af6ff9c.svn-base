package com.zssq.credit.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: TeamInfo  
 * @Description: 班组实体  
 * @author CaiZhaohui  
 * @date 2017年5月19日  
 *
 */
public class TeamInfo implements RowMapper<TeamInfo>,Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 班组编号 */
	private String teamCode;
	
	/** 所属行政组织编号 */
	private String orgCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Override
	public TeamInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TeamInfo teamInfo = new TeamInfo();
		teamInfo.setTeamCode(rs.getString("team_code"));
		teamInfo.setOrgCode(rs.getString("org_code"));
		return teamInfo;
	}
	
}
