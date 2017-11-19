package com.zssq.relation.pojo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: MblogInfo
 * @Description: 微博信息对象
 * @author Mr.B
 * @date 2017年3月15日
 *
 */
public class MblogInfo implements RowMapper<MblogInfo>, Serializable {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Byte isDelete = 0;

	private Long createTime = 0L;

	private Long modify_time = 0L;

	private String mblogCode = "";

	private String orgCode = "";

	private String userCode = "";

	private String teamCode = "";

	private String sourceMblogCode = "";

	private String sourceUserCode = "";

	private Byte mblogDepend = 0;

	private Byte mblogSource = 0;

	private Byte isShield = 0;

	private Long publishTime = 0L;

	private String tenantCode = "";

	private String dynamicCode = "";

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModify_time() {
		return modify_time;
	}

	public void setModify_time(Long modify_time) {
		this.modify_time = modify_time;
	}

	public String getMblogCode() {
		return mblogCode;
	}

	public void setMblogCode(String mblogCode) {
		this.mblogCode = mblogCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getSourceMblogCode() {
		return sourceMblogCode;
	}

	public void setSourceMblogCode(String sourceMblogCode) {
		this.sourceMblogCode = sourceMblogCode;
	}

	public String getSourceUserCode() {
		return sourceUserCode;
	}

	public void setSourceUserCode(String sourceUserCode) {
		this.sourceUserCode = sourceUserCode;
	}

	public Byte getMblogDepend() {
		return mblogDepend;
	}

	public void setMblogDepend(Byte mblogDepend) {
		this.mblogDepend = mblogDepend;
	}

	public Byte getMblogSource() {
		return mblogSource;
	}

	public void setMblogSource(Byte mblogSource) {
		this.mblogSource = mblogSource;
	}

	public Byte getIsShield() {
		return isShield;
	}

	public void setIsShield(Byte isShield) {
		this.isShield = isShield;
	}

	public Long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public String getDynamicCode() {
		return dynamicCode;
	}

	public void setDynamicCode(String dynamicCode) {
		this.dynamicCode = dynamicCode;
	}


		
		

	@Override
	public MblogInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		MblogInfo mblogInfo = new MblogInfo();
		mblogInfo.setCreateTime(rs.getLong("create_time"));
		mblogInfo.setDynamicCode(rs.getString("dynamic_code"));
		mblogInfo.setIsDelete(rs.getByte("is_delete"));
		mblogInfo.setIsShield(rs.getByte("is_shield"));
		mblogInfo.setMblogCode(rs.getString("mblog_code"));
		mblogInfo.setMblogDepend(rs.getByte("mblog_depend"));
		mblogInfo.setMblogSource(rs.getByte("mblog_source"));
		mblogInfo.setModify_time(rs.getLong("modify_time"));
		mblogInfo.setOrgCode(rs.getString("org_code"));
		mblogInfo.setPublishTime(rs.getLong("publish_time"));
		mblogInfo.setSourceMblogCode(rs.getString("source_mblog_code"));
		mblogInfo.setSourceUserCode(rs.getString("source_user_code"));
		mblogInfo.setTeamCode(rs.getString("team_code"));
		mblogInfo.setTenantCode(rs.getString("tenant_code"));
		mblogInfo.setUserCode(rs.getString("user_code"));
		return mblogInfo;
	}

}