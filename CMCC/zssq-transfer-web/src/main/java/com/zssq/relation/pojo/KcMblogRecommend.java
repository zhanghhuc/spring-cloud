package com.zssq.relation.pojo;

import java.util.Date;

public class KcMblogRecommend {
    private Integer id;

    private Long mblogId;

    private Integer proId;

    private Integer cityId;

    private Integer teamId;

    private Integer pubId;

    private Integer userId;

    private Date createtime;

    private Integer state;

    private Integer logo;
    
    private String mBlogCode;//微博code
    
    private String userCode;
    
    private String teamCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMblogId() {
        return mblogId;
    }

    public void setMblogId(Long mblogId) {
        this.mblogId = mblogId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLogo() {
        return logo;
    }

    public void setLogo(Integer logo) {
        this.logo = logo;
    }

	public String getmBlogCode() {
		return mBlogCode;
	}

	public void setmBlogCode(String mBlogCode) {
		this.mBlogCode = mBlogCode;
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
    
    
}