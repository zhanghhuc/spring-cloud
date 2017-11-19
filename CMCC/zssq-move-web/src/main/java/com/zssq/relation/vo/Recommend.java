package com.zssq.relation.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class Recommend implements RowMapper<Recommend>, Serializable{

	    /**  
	    * @Fields serialVersionUID : TODO
	    */  
	private static final long serialVersionUID = 1L;
	private Integer ID;
	private Integer RECO_ID;
	
	private Integer MBLOG_ID;
	private Integer BLOG_ID;
	private Date CREATETIME;
	private Date CREATE_TIME;
	private Integer USER_ID;
	private Integer TEAM_ID;
	private Integer PRO_ID;
	private Integer CITY_ID;
	private Integer PROVINCE_ID;
	private Integer STATE;

	
	
	//辅助导入subjectInfo
	private String subject_code;


	public String getSubject_code() {
		return subject_code;
	}

	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}

	public Integer getRECO_ID() {
		return RECO_ID;
	}

	public void setRECO_ID(Integer rECO_ID) {
		RECO_ID = rECO_ID;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public Integer getPRO_ID() {
		return PRO_ID;
	}

	public void setPRO_ID(Integer pRO_ID) {
		PRO_ID = pRO_ID;
	}

	public Integer getCITY_ID() {
		return CITY_ID;
	}

	public void setCITY_ID(Integer cITY_ID) {
		CITY_ID = cITY_ID;
	}

	public Integer getPROVINCE_ID() {
		return PROVINCE_ID;
	}

	public void setPROVINCE_ID(Integer pROVINCE_ID) {
		PROVINCE_ID = pROVINCE_ID;
	}

	public Integer getTEAM_ID() {
		return TEAM_ID;
	}

	public void setTEAM_ID(Integer tEAM_ID) {
		TEAM_ID = tEAM_ID;
	}

	public Integer getSTATE() {
		return STATE;
	}

	public void setSTATE(Integer sTATE) {
		STATE = sTATE;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getMBLOG_ID() {
		return MBLOG_ID;
	}

	public void setMBLOG_ID(Integer mBLOG_ID) {
		MBLOG_ID = mBLOG_ID;
	}

	public Integer getBLOG_ID() {
		return BLOG_ID;
	}
	public void setBLOG_ID(Integer bLOG_ID) {
		
		BLOG_ID = bLOG_ID;
	}

	public Date getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public Integer getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(Integer uSER_ID) {
		USER_ID = uSER_ID;
	}


	@Override
	public Recommend mapRow(ResultSet rs, int rowNum) throws SQLException {
		Recommend topic = new Recommend();
		topic.setID(rs.getInt("ID"));
		topic.setRECO_ID(rs.getInt("RECO_ID"));
		topic.setMBLOG_ID(rs.getInt("MBLOG_ID"));
		topic.setBLOG_ID(rs.getInt("BLOG_ID"));
		topic.setCREATE_TIME(rs.getDate("CREATE_TIME"));
		topic.setCREATETIME(rs.getDate("CREATETIME"));
		topic.setUSER_ID(rs.getInt("USER_ID"));
		topic.setTEAM_ID(rs.getInt("TEAM_ID"));
		topic.setSTATE(rs.getInt("STATE"));
		topic.setSubject_code(rs.getString("subject_code"));
		return topic;
	}

}
