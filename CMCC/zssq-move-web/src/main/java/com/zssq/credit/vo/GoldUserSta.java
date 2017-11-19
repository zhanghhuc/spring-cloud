package com.zssq.credit.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @ClassName: GoldUserSta  
 * @Description: 源表个人积分、金币账户
 * @author CaiZhaohui  
 * @date 2017年5月17日  
 *
 */
public class GoldUserSta extends GoldSta implements RowMapper<GoldUserSta>,Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 用户编号 */
	private String userCode;
	
	/** 所属行政组织编号 */
	private String orgCode;
	
	/** 非空ID */
	private String id;
	
	/** 用户ID */
	private Integer userId;
	
	/** 积分余额 */
	private Double pointNum;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
	/** 逻辑删除：0-未删除，1-删除 */
	private Integer delFalg;
	
	/** 班组编号 */
	private Integer teamId;
	
	/** 市编号 */
	private Integer cityId;
	
	/** 省编号 */
	private Integer provinceId;
	
	/** 积分编号 */
	private Integer pointId;
	
	/** 金币余额 */
	private Double goldCoin;
	
	/** 级别 */
	private Integer level;
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getPointNum() {
		return pointNum;
	}

	public void setPointNum(Double pointNum) {
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

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	public Double getGoldCoin() {
		return goldCoin;
	}

	public void setGoldCoin(Double goldCoin) {
		this.goldCoin = goldCoin;
	}

	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public GoldUserSta mapRow(ResultSet rs, int rowNum) throws SQLException {
		GoldUserSta goldUserStaMove = new GoldUserSta();
		goldUserStaMove.setId(rs.getString("ID"));
		goldUserStaMove.setUserId(rs.getInt("USER_ID"));
		goldUserStaMove.setPointNum(rs.getDouble("POINT_NUM"));
		goldUserStaMove.setCreateTime(rs.getDate("CREATE_TIME"));
		goldUserStaMove.setUpdateTime(rs.getDate("UPDATE_TIME"));
		goldUserStaMove.setDelFalg(rs.getInt("DEL_FALG"));
		goldUserStaMove.setTeamId(rs.getInt("TEAM_ID"));
		goldUserStaMove.setCityId(rs.getInt("CITY_ID"));
		goldUserStaMove.setProvinceId(rs.getInt("PROVINCE_ID"));
		goldUserStaMove.setPointId(rs.getInt("POINT_ID"));
		goldUserStaMove.setGoldCoin(rs.getDouble("GOLD_COIN"));
		goldUserStaMove.setLevel(rs.getInt("LEVEL"));
		return goldUserStaMove;
	}

}
