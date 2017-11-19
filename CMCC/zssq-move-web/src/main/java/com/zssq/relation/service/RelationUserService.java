package com.zssq.relation.service;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;
import com.zssq.relation.vo.UserInfoVo;

@Service
@DataSource(DataSourceConstants.MYSQL_AUTH)
public class RelationUserService {

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
		try{
			UserInfoVo user = jdbcTemplate.queryForObject(UserInfoVo.getSelectStatement(userId+""), new Object[]{}, new BeanPropertyRowMapper<UserInfoVo>(UserInfoVo.class));
			if(null == user){
				return new UserInfoVo();
			}
			return user;
		}catch(Exception e){
			System.err.println("查询不到用户信息");
			return new UserInfoVo();
		}
	} 
	
	
	/**
	 * @Function getUserOrgCode
	 * @Description 用户组织信息
	 * @param userId
	 * @return
	 */
	public String getUserOrgCode(Integer userId){
		String userOrgCode="";
		try{
			String sql="SELECT `org_code` FROM `transfer_user_info` WHERE `user_id`='"+userId+"'";
			userOrgCode = jdbcTemplate.queryForObject(sql, new Object[]{}, new BeanPropertyRowMapper<String>(String.class));
			return userOrgCode;
		}catch(Exception e){
			System.err.println("查询不到用户组织信息");
			return userOrgCode;
		}
	} 
	
	/**
	 * @Function getParentOrgInfo
	 * @Description 获取父orgcode
	 * @param orgCode
	 * @return
	 */
	public String getParentOrgInfo(String orgCode){
			String sql="SELECT `parent_code` FROM `transfer_org_info` WHERE sys_org_code ='"+orgCode+"'";
			String parentOrg = jdbcTemplate.queryForObject(sql, String.class);
			return parentOrg;
	}
	
	/**
	 * @Function getParentOrgInfo
	 * @Description 通过orgId 获取orgCode
	 * @param orgId
	 * @return
	 */
	public String getOrgCodeByOrgID(Integer orgId){
			String sql="SELECT `src_code` FROM `transfer_org_info` WHERE `org_id` ="+orgId+"";
			String org = jdbcTemplate.queryForObject(sql, String.class);
			return org;
	}
	
	
	/**
	 * @Function getGroupCode
	 * @Description 获取集团code
	 * @param orgId
	 * @return
	 */
	public String getGroupCode(){
			String sql="SELECT DISTINCT `sys_org_code` FROM `transfer_org_info` LEFT JOIN `sys_org_level`ON transfer_org_info.`sys_org_type` =sys_org_level.`level_code`WHERE sys_org_level.`level` = 'A' LIMIT 0, 1 ";
			String org = jdbcTemplate.queryForObject(sql, String.class);
			return org;
	}
}
