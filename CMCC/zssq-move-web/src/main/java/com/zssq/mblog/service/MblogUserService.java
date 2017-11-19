package com.zssq.mblog.service;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.mblog.vo.UserInfoVo;

@Service
@DataSource(DataSourceConstants.MYSQL_MBLOG)
public class MblogUserService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	    * @Title: getUserInfo  
	    * @Description: 获取用户信息
	    * @param userId	用户ID
		* @return UserInfoVo    返回类型
	 */
	public UserInfoVo getUserInfo(Integer userId){
		if(null == userId){
			return null;
		}
		try{
			UserInfoVo user = jdbcTemplate.queryForObject(UserInfoVo.getSelectStatement(userId+""), new Object[]{}, new BeanPropertyRowMapper<UserInfoVo>(UserInfoVo.class));
			if(null == user){
				return null;
			}
			return user;
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	} 
}
