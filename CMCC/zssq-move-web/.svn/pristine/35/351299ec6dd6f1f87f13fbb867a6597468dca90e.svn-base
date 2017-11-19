package com.zssq.relation.service;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.relation.vo.TeamInfoVo;

@Service
@DataSource(DataSourceConstants.MYSQL_TEAM)
public class RelationTeamService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	    * @Title: getTeamInfo  
	    * @Description: 获取班组信息
	    * @param teamId		班组ID
		* @return TeamInfoVo    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_RELATION)
	public TeamInfoVo getTeamInfo(Integer teamId){
			try {
				TeamInfoVo team = jdbcTemplate.queryForObject(TeamInfoVo.getSelectStatement(teamId), new Object[]{}, new BeanPropertyRowMapper<TeamInfoVo>(TeamInfoVo.class));
				return team;
			} catch (DataAccessException e) {
				return new TeamInfoVo();
			}
			
	} 
}
