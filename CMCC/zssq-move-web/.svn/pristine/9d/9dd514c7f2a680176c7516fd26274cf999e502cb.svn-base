package com.zssq.mblog.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TeamInfoVo implements RowMapper<TeamInfoVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;

	private Integer teamId;
	private String teamCode;
	private String orgCode;
	
	
	public Integer getTeamId() {
		return teamId;
	}


	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}


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


	/**
	 * 
	    * @Title: getSelectStatement  
	    * @Description: 获取查询语句
	    * @param teamId
		* @return String    返回类型
	 */
	public static String getSelectStatement(Integer teamId){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" team_code,org_code ")
		  .append(" FROM ")
		  .append(" team_info ")
		  .append(" WHERE ")
		  .append(" team_id = " + teamId );
		return sb.toString();
	}
	
	@Override
	public TeamInfoVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TeamInfoVo vo = new TeamInfoVo();
		vo.setOrgCode(rs.getString("org_code"));
		vo.setTeamCode(rs.getString("team_code"));
		return vo;
	}

}
