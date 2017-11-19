package com.zssq.team.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zssq.datasource.DataSourceConstants;
import com.zssq.datasource.DynamicDataSourceHolder;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.team.vo.KcTeam;
import com.zssq.team.vo.KcTeamUser;
import com.zssq.team.vo.SysOrgInfo;

@Service
public class TeamService {
	
	@Resource
	private JdbcTemplate jdbcTemplate; //zssq_mblog
	
	
	/**
	 * @Function teamInfoInsert
	 * @Description 班组基本信息插入
	 */
	public void teamInfoInsert(){
		
		//读取db2班组数据
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
		String sql = " SELECT "+
			"	team_id,TEAM_CODE,TEAM_NAME,TEAM_DESC,TEAM_LOGO,"+ 
			"	ISNUMBERONE,TEAM_STATE,CREATE_DATE,ORG_ID,PROVINCE_ID"+
			" FROM"+
			"	kc_team";
		List<KcTeam> teamList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<KcTeam>(KcTeam.class));

		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		//删除班组数据
		sql = "delete from team_info where team_code like 'banzu%'";
		jdbcTemplate.execute(sql);
		//插入班组基本信息
		List<KcTeam> insertList = new ArrayList<KcTeam>();
		for (int i = 0; i < teamList.size(); i++) {
			insertList.add(teamList.get(i));
			if((i+1)%200==0){
				teamInfoInsert(insertList);
				insertList.clear();
			}
		}
		if(insertList.size()>0)
			teamInfoInsert(insertList);
		
		//变更班组类型
		sql = "UPDATE team_info"+
			 " SET team_type = 1"+
			 " WHERE "+
		     " team_type = 3 OR team_type = 4";
		jdbcTemplate.execute(sql);

		//读取auth表中org信息
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_AUTH);
		//查询auth  org
		sql = "SELECT org_id,man_org_code FROM transfer_org_info";
		List<SysOrgInfo> orgList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<SysOrgInfo>(SysOrgInfo.class));
		
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		
		//删表team新建的org表
		sql = "drop table IF EXISTS zssq_team.team_org";
		jdbcTemplate.execute(sql);
		
		//建中间表
		sql = "CREATE TABLE team_org ("+
			"	  org_id bigint(20),"+
			"	  man_org_code varchar(32)"+
			"	)";
		jdbcTemplate.execute(sql);
		
		
		List<SysOrgInfo> orgInsertList = new ArrayList<SysOrgInfo>();
		//插入
		for (int i = 0; i < orgList.size(); i++) {
			orgInsertList.add(orgList.get(i));
			if((i+1)%200==0){
				orgInsert(orgInsertList);
				orgInsertList.clear();
			}
		}
		if(orgInsertList.size()>0)
			orgInsert(orgInsertList);
		
		
		//更改team的org
		sql = "update team_info t1 set org_code=("+
			"	select t.man_org_code from team_org t where t.org_id=t1.org_id)";
		jdbcTemplate.execute(sql);
		
		
		//删表team新建的org表
		sql = "drop table zssq_team.team_org";
		jdbcTemplate.execute(sql);
		
	}
	
	
	private void teamInfoInsert(List<KcTeam> kcTeamList){
		String sql = "INSERT INTO team_info "+
				"(team_id,team_code,team_name,team_intro,team_icon,"
				+ "team_type,is_dissolve,create_time,is_record,org_id) values";
		for (int i = 0; i < kcTeamList.size(); i++) {
			KcTeam kcTeam = kcTeamList.get(i);
			sql += "(";
			sql += kcTeam.getTeamId()+",'"+kcTeam.getTeamCode()+"','"+kcTeam.getTeamName()+"','"+kcTeam.getTeamDesc()+"','";
			sql += kcTeam.getTeamLogo()+"',"+kcTeam.getIsnumberone()+","+kcTeam.getTeamState()+","+kcTeam.getCreateDate().getTime();
			sql += ",0,";
			if(kcTeam.getOrgId() == null){
				sql +=	kcTeam.getProvinceId()	;
			}else{
				sql +=	kcTeam.getOrgId()	;
			}
			sql += "),";
		} 
		sql = sql.substring(0, sql.length()-1);
		jdbcTemplate.execute(sql);
	}
	
	
	private void orgInsert(List<SysOrgInfo> orgList){
		String sql = "INSERT INTO team_org (org_id,man_org_code) values";
		for (int i = 0; i < orgList.size(); i++) {
			SysOrgInfo orgInfo = orgList.get(i);
			sql += "(";
			sql += orgInfo.getOrgId()+",'"+orgInfo.getManOrgCode()+"'";
			sql += "),";
		} 
		sql = sql.substring(0, sql.length()-1);
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * @Function teamMemberInsert
	 * @Description 班组成员插入
	 */
	public void teamMemberInsert(){
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		String sql = "delete from team_member where team_code like 'banzu%'";
		jdbcTemplate.execute(sql);
		
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
		sql = "select  id,concat (hex (RAND ()), hex (RAND ())) memberCode,"
				+ "t2.team_code,t3.user_code,t1.TEAM_POSITION,t1.JOIN_DATE "
				+ "from kc_team_user t1 "
				+ "left join kc_team t2 on t1.team_id=t2.team_id "
				+ "left join kc_user t3 on t3.user_id=t1.user_id";
		List<KcTeamUser> teamUserList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<KcTeamUser>(KcTeamUser.class));
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		List<KcTeamUser> insertList = new ArrayList<KcTeamUser>();
		for (int i = 0; i < teamUserList.size(); i++) {
			insertList.add(teamUserList.get(i));
			if((i+1)%350==0){
				teamMemberInsert(insertList);
				insertList.clear();
			}
		}
		if(insertList.size()>0)
			teamMemberInsert(insertList);
		
	}
	
	
	private void teamMemberInsert(List<KcTeamUser> insertList){
		String sql = "INSERT INTO team_member "+
				"(team_member_code,team_code,user_code,is_leader,create_time,team_member_id) values";
		for (int i = 0; i < insertList.size(); i++) {
			KcTeamUser kcTeamUser = insertList.get(i);
			sql += "('";
			sql += kcTeamUser.getMemberCode()+"','"+kcTeamUser.getTeamCode()+"','"+kcTeamUser.getUserCode()+"',"+kcTeamUser.getTeamPosition()+",";
			sql += kcTeamUser.getJoinDate().getTime()+","+kcTeamUser.getId();
			sql += "),";
		} 
		sql = sql.substring(0, sql.length()-1);
		jdbcTemplate.execute(sql);
	}
	
	
	/**
	 * @Function honorTeamInsert
	 * @Description 百强班组相关插入
	 */
	public void honorTeamInsert(){
		
		String electCode = "20170101";
		long electTime = new Date().getTime()*1000/1000;
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.DB2_ACTIVITY);
		String sql = "select team_code from kc_team t where t.istop = 1";
		List<KcTeam> teamList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<KcTeam>(KcTeam.class));
		
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		sql = "delete from team_elect where team_elect_code = '"+electCode+"'";
		jdbcTemplate.execute(sql);//删除评选
		sql = "delete from team_recommend where team_elect_code = '"+electCode+"'";
		jdbcTemplate.execute(sql);//删除百强
		sql = "delete from team_course where team_elect_code = '"+electCode+"'";
		jdbcTemplate.execute(sql);//删除历程
		
		//插入评选
		sql = "insert into team_elect ("+
			 "	  team_elect_code,"+
			"	  elect_title,"+
			"	  elect_detail,"+
			"	  elect_start_time,"+
			"	  elect_end_time,"+
			"	  is_latest,"+
			"	  user_code,"+
			"	  org_code"+
			"	) "+
			"	values"+
			"	  ("+
			"	    '"+electCode+"',"+
			"	    '',"+
			"	    '',"+
			"	    "+electTime+","+
			"	    "+electTime+","+
			"	    0,'',''"+
			"	  ) ";
		jdbcTemplate.execute(sql);
		
		//百强班组表
		sql = "insert into team_recommend "+ 
			"(team_recommend_code,team_elect_code,team_code,recommend_reason,elect_time,is_excellent,user_code,is_honor) values";
		for (int i = 0; i < teamList.size(); i++) {
			sql += "(";
			sql += "'"+UUIDHelper.getUUID()+"','"+electCode+"','"+teamList.get(i).getTeamCode()+"','',";
			sql += electTime+",1,'',0";
			sql += "),";
		}
		sql = sql.substring(0, sql.length()-1);
		jdbcTemplate.execute(sql);
		
		//更新org
		sql = "update "+
			"  team_recommend t1 "+
			"set"+
			"  t1.org_code = "+
			"  (select "+
			"    t2.org_code "+
			"  from"+
			"    team_info t2 "+
			"  where t2.team_code = t1.team_code)"+
			" where t1.team_elect_code='"+electCode+"'";
		jdbcTemplate.execute(sql);
		
		//班组历程表
		sql =" INSERT INTO team_course (team_course_code,"+
			"	team_elect_code,"+
			"	team_code,"+
			"	current_recommend_reason,"+
			"	current_org_code,"+
			"	current_org_type,"+
			"	super_org_code,"+
			"	is_recommend,"+
			"	user_code,"+
			"	create_time )"+
			"	SELECT "+
			"	  REPLACE(UUID(), '-', ''),"+
			"	  t.team_elect_code,"+
			"	  t.team_code,"+
			"	  '',"+
			"	  t.org_code,"+
			"	  'A',"+
			"	  '',"+
			"	  1,'',"+
			"	  t.elect_time"+
			"	FROM"+
			"	  team_recommend t"+ 
			"	WHERE t.team_elect_code ='"+electCode+"'";
		jdbcTemplate.execute(sql);
		
	}
	
	
	/**
	 * @Function selectTeamCodeById
	 * @Description 根据teamId查询teamcode
	 * @return
	 */
	public String selectTeamCodeById(String teamId){
		DynamicDataSourceHolder.setDataSource(DataSourceConstants.MYSQL_TEAM);
		String sql = "select team_code from team_info where team_id = "+teamId+"";;
		String teamCode = jdbcTemplate.queryForObject(sql, String.class);
		return teamCode;
	}
	
	
}

//class UUIDHelper {
//	public static void main(String[] args) {
//		System.out.println(getUUID());
//	}
//	
//	/** 
//     * 获得一个UUID 
//     * @return String UUID 
//     */ 
//    public static String getUUID(){ 
//        String s = UUID.randomUUID().toString(); 
//        //去掉“-”符号 
//        return removeStr("-",s); 
//    } 
//    
//    public static String removeStr(String str,String src){
//    	String[] srcs = src.split(str);
//    	
//    	StringBuffer sb = new StringBuffer();
//    	
//    	for(String s : srcs){
//    		sb.append(s);
//    	}
//    	
//    	return sb.toString();
//    } 
//}
