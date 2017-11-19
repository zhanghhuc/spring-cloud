package com.zssq.mblog.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MysqlMblogIdCodeVo implements RowMapper<MysqlMblogIdCodeVo>, Serializable{

    /**  
    * @Fields serialVersionUID : TODO
    */  
	private static final long serialVersionUID = 1L;

	private Long mblogId;
	private String mblogCode = "";
	private String userCode = "";
	
	
	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public Long getMblogId() {
		return mblogId;
	}


	public void setMblogId(Long mblogId) {
		this.mblogId = mblogId;
	}


	public String getMblogCode() {
		return mblogCode;
	}


	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}

	/**
	 * 
	    * @Title: getInsertStatement  
	    * @Description: 获取插入语句
		* @return String    返回类型
	 */
	public static String getInsertStatement(){
		StringBuilder sb = new StringBuilder();
		sb.append(" insert into mblog_id_code ")
			.append(" ( ")
			.append(" mblog_id, ")
			.append(" mblog_code, ")
			.append(" user_code ")
			.append(" ) ")
			.append(" values (?,?,?) ");
		return sb.toString();
	}
	
	/**
	 * 
	    * @Title: getCreateStatement  
	    * @Description:获取创建语句
		* @return String[]    返回类型
	 */
	public static String[] getCreateStatement(){
		String[] statement = new String[2];
		StringBuilder sb = new StringBuilder();
		sb.append(" CREATE TABLE mblog_id_code ")
			.append(" ( ")
			.append(" mblog_id bigint(20) NOT NULL, ")
			.append(" mblog_code varchar(32) NOT NULL, ")
			.append(" user_code varchar(32) NOT NULL, ")
			.append(" KEY in_id (mblog_id) USING HASH ")
			.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8; ");
		statement[0] = " DROP TABLE IF EXISTS mblog_id_code ";
		statement[1] = sb.toString();
		return statement;
	}
	
	/**
	 * 
	    * @Title: getSelectStatement  
	    * @Description: 获取查询语句
	    * @param mblogId
		* @return String    返回类型
	 */
	public static String getSelectStatement(String mblogId){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ")
		  .append(" mblog_id,mblog_code,user_code ")
		  .append(" FROM ")
		  .append(" mblog_id_code ")
		  .append(" WHERE ")
		  .append(" mblog_id = "+mblogId+" ");
		return sb.toString();
		  
	}

	@Override
	public MysqlMblogIdCodeVo mapRow(ResultSet rs, int rowNum) throws SQLException {
		MysqlMblogIdCodeVo vo = new MysqlMblogIdCodeVo();
		vo.setMblogCode(rs.getString("mblog_code"));
		vo.setUserCode(rs.getString("user_code"));
		vo.setMblogId(rs.getLong("mblog_id"));
		return vo;
	}

}
