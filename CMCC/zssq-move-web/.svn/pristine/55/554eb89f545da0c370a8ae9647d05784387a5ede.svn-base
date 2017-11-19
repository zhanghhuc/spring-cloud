package com.zssq.credit.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: GoldTeamSta  
 * @Description: 源表班组积分、金币账户  
 * @author CaiZhaohui  
 * @date 2017年5月17日  
 *
 */
public class GoldTeamSta extends GoldSta implements RowMapper<GoldTeamSta>,Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 班组编号 */
	private String teamCode;
	
	/** 所属行政组织编号 */
	private String orgCode;
	
	/** 非空ID */
	private String id;
	
	/** 班组ID */
	private Integer teamId;
	
	/** 积分余额 */
	private BigDecimal pointNum;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
	/** 逻辑删除：0-未删除，1-删除 */
	private Integer delFalg;
	
	/** 市编号 */
	private Integer cityId;
	
	/** 省编号 */
	private Integer provinceId;
	
	/** 金币余额 */
	private BigDecimal goldCoin;
	
	/** 级别 */
	private Integer level;
	
	private Integer attendNum;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public BigDecimal getPointNum() {
		return pointNum;
	}

	public void setPointNum(BigDecimal pointNum) {
		this.pointNum = pointNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDelFalg() {
		return delFalg;
	}

	public void setDelFalg(Integer delFalg) {
		this.delFalg = delFalg;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public BigDecimal getGoldCoin() {
		return goldCoin;
	}

	public void setGoldCoin(BigDecimal goldCoin) {
		this.goldCoin = goldCoin;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(Integer attendNum) {
		this.attendNum = attendNum;
	}

	@Override
	public GoldTeamSta mapRow(ResultSet rs, int rowNum) throws SQLException {
		GoldTeamSta goldTeamStaMove = new GoldTeamSta();
		goldTeamStaMove.setId(rs.getString("ID"));
		goldTeamStaMove.setTeamId(rs.getInt("TEAM_ID"));
		goldTeamStaMove.setPointNum(rs.getBigDecimal("POINT_NUM"));
		goldTeamStaMove.setCreateTime(rs.getDate("CREATE_TIME"));
		goldTeamStaMove.setUpdateTime(rs.getDate("UPDATE_TIME"));
		goldTeamStaMove.setDelFalg(rs.getInt("DEL_FALG"));
		goldTeamStaMove.setCityId(rs.getInt("CITY_ID"));
		goldTeamStaMove.setProvinceId(rs.getInt("PROVINCE_ID"));
		goldTeamStaMove.setGoldCoin(rs.getBigDecimal("GOLD_COIN"));
		goldTeamStaMove.setLevel(rs.getInt("LEVEL"));
		goldTeamStaMove.setLevel(rs.getInt("ATTEND_NUM"));
		return goldTeamStaMove;
	}

}
