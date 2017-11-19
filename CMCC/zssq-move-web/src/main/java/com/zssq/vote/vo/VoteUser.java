package com.zssq.vote.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class VoteUser implements RowMapper<VoteUser>, Serializable{
	private static final long serialVersionUID = -6188340586650416377L;

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

	@Override
	public VoteUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		VoteUser user = new VoteUser();
		user.setVoteUserId(rs.getInt("VOTE_USER_ID"));
		user.setVoteId(rs.getInt("VOTE_ID"));
		user.setUserId(rs.getInt("USER_ID"));
		user.setVoteDate(rs.getDate("VOTE_DATE"));
		user.setVoteOptions(rs.getInt("VOTE_OPTIONS"));
		user.setTeamId(rs.getInt("TEAM_ID"));
		user.setCityId(rs.getInt("CITY_ID"));
		user.setProvinceId(rs.getInt("PROVINCE_ID"));
		return user;
	}
}