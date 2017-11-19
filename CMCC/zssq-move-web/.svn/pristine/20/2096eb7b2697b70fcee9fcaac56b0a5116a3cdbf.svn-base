package com.zssq.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.blog.vo.TeamInfo;
import com.zssq.blog.vo.UserInfo;
import com.zssq.datasource.DataSource;
import com.zssq.datasource.DataSourceConstants;

/**
 * 
 * @ClassName: BlogThirdService  
 * @Description: 第三方调用Service  
 * @author ZKZ  
 * @date 2017年5月24日  
 *
 */
@Service("blogThirdService")
public class BlogThirdService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @Title: getUserCode  
	 * @Description: 获取人员编号
	 * @return: String    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_AUTH)
	public String getUserCode(int userId) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("user_code ");
		sql.append("FROM ");
		sql.append("transfer_user_info ");
		sql.append("WHERE ");
		sql.append("user_id = ?");
		
		// 参数
		Object[] params = new Object[1];
		params[0] = userId;
		
		// 查询
		List<String> userCodeList = jdbcTemplate.queryForList(sql.toString(), params, String.class);
		if (userCodeList == null || userCodeList.isEmpty()) {
			return "";
		} else {
			return userCodeList.get(0);
		}
	}
	
	/**
	 * 
	 * @Title: getUserInfo  
	 * @Description: 获取人员信息
	 * @param userId
	 * @return: UserInfo    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_AUTH)
	public UserInfo getUserInfo(int userId) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("ui.user_code AS userCode, ");
		sql.append("ui.saas_tenant_code AS tenantCode, ");
		sql.append("oi.man_org_code AS orgCode ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("user_code, ");
		sql.append("org_code, ");
		sql.append("saas_tenant_code ");
		sql.append("FROM ");
		sql.append("transfer_user_info ");
		sql.append("WHERE ");
		sql.append("user_id = ? ");
		sql.append(") ui ");
		sql.append("LEFT JOIN ");
		sql.append("transfer_org_info oi ");
		sql.append("ON ");
		sql.append("ui.org_code = oi.sys_org_code ");
		
		// 参数
		Object[] params = new Object[1];
		params[0] = userId;
		
		// 查询
		List<UserInfo> userList = jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (userList == null || userList.isEmpty()) {
			return null;
		} else {
			return userList.get(0);
		}
	}
	
	/**
	 * 
	 * @Title: getUserInfoList  
	 * @Description: 获取所有用户信息
	 * @return: List<UserInfo>    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_AUTH)
	public List<UserInfo> getUserInfoList() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("ui.user_code AS userCode, ");
		sql.append("ui.saas_tenant_code AS tenantCode, ");
		sql.append("oi.man_org_code AS orgCode ");
		sql.append("FROM ");
		sql.append("transfer_user_info ui ");
		sql.append("LEFT JOIN ");
		sql.append("transfer_org_info oi ");
		sql.append("ON ");
		sql.append("ui.org_code = oi.sys_org_code ");
		
		// 查询
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
	}
	
	/**
	 * 
	 * @Title: getTeamCode  
	 * @Description: 获取班组编号
	 * @param teamId
	 * @return: String    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_TEAM)
	public String getTeamCode(int teamId) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("team_code ");
		sql.append("FROM ");
		sql.append("team_info ");
		sql.append("WHERE ");
		sql.append("team_id = ?");
		
		// 参数
		Object[] params = new Object[1];
		params[0] = teamId;
		
		// 查询
		List<String> teamCodeList = jdbcTemplate.queryForList(sql.toString(), params, String.class);
		if (teamCodeList == null || teamCodeList.isEmpty()) {
			return "";
		} else {
			return teamCodeList.get(0);
		}
	}
	
	/**
	 * 
	 * @Title: getTeamInfo  
	 * @Description: 获取班组信息
	 * @param teamId
	 * @return: TeamInfo    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_TEAM)
	public TeamInfo getTeamInfo(int teamId) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("team_code AS teamCode, ");
		sql.append("org_code AS orgCode, ");
		sql.append("tenant_code AS tenantCode ");
		sql.append("FROM ");
		sql.append("team_info ");
		sql.append("WHERE ");
		sql.append("team_id = ?");
		
		// 参数
		Object[] params = new Object[1];
		params[0] = teamId;
		
		// 查询
		List<TeamInfo> teamList = jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<TeamInfo>(TeamInfo.class));
		if (teamList == null || teamList.isEmpty()) {
			return null;
		} else {
			return teamList.get(0);
		}
	}
	
	/**
	 * 
	 * @Title: getTeamInfoList  
	 * @Description: 获取所有班组信息
	 * @return: List<TeamInfo>    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_TEAM)
	public List<TeamInfo> getTeamInfoList() {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("team_code AS teamCode, ");
		sql.append("org_code AS orgCode, ");
		sql.append("tenant_code AS tenantCode ");
		sql.append("FROM ");
		sql.append("team_info ");
		
		// 查询
		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<TeamInfo>(TeamInfo.class));
	}
	
	/**
	 * 
	 * @Title: getOrgCode  
	 * @Description: 获取组织编号
	 * @param orgId
	 * @return: String    返回类型
	 */
	@DataSource(DataSourceConstants.MYSQL_AUTH)
	public String getOrgCode(int orgId) {
		// 拼接SQL
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("sys_org_code ");
		sql.append("FROM ");
		sql.append("transfer_org_info ");
		sql.append("WHERE ");
		sql.append("org_id = ?");
		
		// 参数
		Object[] params = new Object[1];
		params[0] = orgId;
		
		// 查询
		List<String> orgCodeList = jdbcTemplate.queryForList(sql.toString(), params, String.class);
		if (orgCodeList == null || orgCodeList.isEmpty()) {
			return "";
		} else {
			return orgCodeList.get(0);
		}
	}

}
