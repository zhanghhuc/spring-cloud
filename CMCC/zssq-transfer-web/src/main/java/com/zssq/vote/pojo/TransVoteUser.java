package com.zssq.vote.pojo;

import java.util.Date;

public class TransVoteUser {
    private Integer voteUserId;

    private Integer voteId;

    private Integer userId;

    private Date voteDate;

    private Integer voteOptions;

    private Integer teamId;

    private Integer cityId;

    private Integer provinceId;

    public Integer getVoteUserId() {
        return voteUserId;
    }

    public void setVoteUserId(Integer voteUserId) {
        this.voteUserId = voteUserId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public Integer getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(Integer voteOptions) {
        this.voteOptions = voteOptions;
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
}