package com.zssq.mblog.service;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.mblog.vo.TeamInfoVo;

@Service
@DataSource(DataSourceConstants.MYSQL_MBLOG)
public class MblogTeamService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	    * @Title: getTeamInfo  
	    * @Description: 获取班组信息
	    * @param teamId		班组ID
		* @return TeamInfoVo    返回类型
	 */
	public TeamInfoVo getTeamInfo(Integer teamId){
		if(null == teamId){
			return null;
		}
		try{
			TeamInfoVo team = jdbcTemplate.queryForObject(TeamInfoVo.getSelectStatement(teamId), new Object[]{}, new BeanPropertyRowMapper<TeamInfoVo>(TeamInfoVo.class));
			if(null == team){
				return null;
			}
			return team;
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	} 
}
