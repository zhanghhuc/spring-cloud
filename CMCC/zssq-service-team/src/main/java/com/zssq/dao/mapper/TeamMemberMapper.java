package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.TeamMember;

public interface TeamMemberMapper {
	
	/** 根据唯一标识删除 */
    int deleteByCode(String code);

    /** 动态插入 */
    int insertSelective(TeamMember record);

    /** 根据唯一标识删除 */
    TeamMember selectByCode(String code);

    /** 批量变更为班组长 */
    int updateByCodeSelective(TeamMember record);

    /** 批量插入 */
    int batchInsert(List<TeamMember> memList);
    
    /** 查询班组成员 */
    List<TeamMember> selectPage(TeamMember record);
    
    /** 查询班组成员(总数) */
    int selectPageCount(TeamMember record);
    
    /** 根据班组code返回成员集合 */
    List<TeamMember> selectByTeamCode(String teamCode);
    
    /** 根据班组code返回班组长 */
    List<String> selectLeaderByTeamCode(String teamCode);
    
    /** 根据班组code,查询班组成员 */
    List<String> selectUserCodeByTeamCode(String teamCode);
    
    /** 返回班组code */
    List<String> selectTeamCode(TeamMember record);
    
    /** 拿前三个班组成员 */
    List<String> selectTop3(TeamMember record);
}