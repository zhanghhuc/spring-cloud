package com.zssq.vote.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;


public class Vote  implements RowMapper<Vote>, Serializable{
 
	private static final long serialVersionUID = 8947678572221523384L;

	private Integer voteId;

    private Integer userId;

    private Integer provinceId;

    private Integer cityId;

    private Integer teamId;

    private Integer voteType;

    private Date createTime;

    private String voteTitle;

    private String voteDeclare;

    private Integer optionsType;

    private Date voteEndTime;

    private Integer delFlag;

    private Integer cmtNum;

    private Integer voteNum;

    private Date voteStartTime;

    private Integer flagReco;

    private Integer insteadFlag;

    private Integer insteadUser;

    private Date updateTime;

    private Integer voteDomain;

    private Integer voteFlag;

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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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

    public Integer getVoteType() {
        return voteType;
    }

    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle == null ? null : voteTitle.trim();
    }

    public String getVoteDeclare() {
        return voteDeclare;
    }

    public void setVoteDeclare(String voteDeclare) {
        this.voteDeclare = voteDeclare == null ? null : voteDeclare.trim();
    }

    public Integer getOptionsType() {
        return optionsType;
    }

    public void setOptionsType(Integer optionsType) {
        this.optionsType = optionsType;
    }

    public Date getVoteEndTime() {
        return voteEndTime;
    }

    public void setVoteEndTime(Date voteEndTime) {
        this.voteEndTime = voteEndTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getCmtNum() {
        return cmtNum;
    }

    public void setCmtNum(Integer cmtNum) {
        this.cmtNum = cmtNum;
    }

    public Integer getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Integer voteNum) {
        this.voteNum = voteNum;
    }

    public Date getVoteStartTime() {
        return voteStartTime;
    }

    public void setVoteStartTime(Date voteStartTime) {
        this.voteStartTime = voteStartTime;
    }

    public Integer getFlagReco() {
        return flagReco;
    }

    public void setFlagReco(Integer flagReco) {
        this.flagReco = flagReco;
    }

    public Integer getInsteadFlag() {
        return insteadFlag;
    }

    public void setInsteadFlag(Integer insteadFlag) {
        this.insteadFlag = insteadFlag;
    }

    public Integer getInsteadUser() {
        return insteadUser;
    }

    public void setInsteadUser(Integer insteadUser) {
        this.insteadUser = insteadUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVoteDomain() {
        return voteDomain;
    }

    public void setVoteDomain(Integer voteDomain) {
        this.voteDomain = voteDomain;
    }

    public Integer getVoteFlag() {
        return voteFlag;
    }

    public void setVoteFlag(Integer voteFlag) {
        this.voteFlag = voteFlag;
    }

	@Override
	public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
		Vote vote = new Vote();
		vote.setVoteId(rs.getInt("VOTE_ID"));
		vote.setUserId(rs.getInt("USER_ID"));
		vote.setProvinceId(rs.getInt("PROVINCE_ID"));
		vote.setCityId(rs.getInt("CITY_ID"));
		vote.setTeamId(rs.getInt("TEAM_ID"));
		vote.setVoteType(rs.getInt("VOTE_TYPE"));
		vote.setCreateTime(rs.getDate("CREATE_TIME"));
		vote.setVoteTitle(rs.getString("VOTE_TITLE"));
		vote.setVoteDeclare(rs.getString("VOTE_DECLARE"));
		vote.setOptionsType(rs.getInt("OPTIONS_TYPE"));
		vote.setVoteEndTime(rs.getDate("VOTE_END_TIME"));
		vote.setDelFlag(rs.getInt("DEL_FLAG"));
		vote.setCmtNum(rs.getInt("CMT_NUM"));
		vote.setVoteNum(rs.getInt("VOTE_NUM"));
		vote.setVoteStartTime(rs.getDate("VOTE_START_TIME"));
		vote.setFlagReco(rs.getInt("FLAG_RECO"));
		vote.setInsteadFlag(rs.getInt("INSTEAD_FLAG"));
		vote.setInsteadUser(rs.getInt("INSTEAD_USER "));
		vote.setUpdateTime(rs.getDate("UPDATE_TIME"));
		vote.setVoteDomain(rs.getInt("VOTE_DOMAIN"));
		vote.setVoteFlag(rs.getInt("VOTE_DOMAIN"));
		
		return vote;
	}

	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", userId=" + userId + ", provinceId=" + provinceId + ", cityId=" + cityId
				+ ", teamId=" + teamId + ", voteType=" + voteType + ", createTime=" + createTime + ", voteTitle="
				+ voteTitle + ", voteDeclare=" + voteDeclare + ", optionsType=" + optionsType + ", voteEndTime="
				+ voteEndTime + ", delFlag=" + delFlag + ", cmtNum=" + cmtNum + ", voteNum=" + voteNum
				+ ", voteStartTime=" + voteStartTime + ", flagReco=" + flagReco + ", insteadFlag=" + insteadFlag
				+ ", insteadUser=" + insteadUser + ", updateTime=" + updateTime + ", voteDomain=" + voteDomain
				+ ", voteFlag=" + voteFlag + "]";
	}
	
}