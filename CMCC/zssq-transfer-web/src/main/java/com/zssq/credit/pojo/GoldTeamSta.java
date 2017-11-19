package com.zssq.credit.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class GoldTeamSta {
    private String id;

    private Integer teamId;

    private BigDecimal pointNum;

    private Date createTime;

    private Date updateTime;

    private Integer delFalg;

    private Integer cityId;

    private Integer provinceId;

    private BigDecimal goldCoin;

    private Integer level;

    private Integer attendNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
}