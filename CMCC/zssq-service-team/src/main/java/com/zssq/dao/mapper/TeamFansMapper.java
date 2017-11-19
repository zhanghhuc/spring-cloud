package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.TeamFans;

public interface TeamFansMapper {

	/** 动态插入 */
	int insertSelective(TeamFans record);

    /** 按唯一标识查询 */
    TeamFans selectByCode(String code);
    
    /** 查询班组粉丝 */
    List<TeamFans> selectPage(TeamFans record);
    
    /** 查询班组粉丝(数量) */
    int selectCount(TeamFans record);
    
    /** 查询我关注的班组 */
    List<TeamFans> selectConcersTeamPage(TeamFans record);
    
    /** 查询我关注的班组(数量) */
    int selectConcersTeamCount(TeamFans record);
    
    /** 按唯一标识删除 */
    int deleteByCode(String code);
    
    /** 按唯一标识查询 */
    List<String> selectConcernsTeam(String userCode);
    
    /** 根据人和班组删除 */
    int unfollow(TeamFans record);
}