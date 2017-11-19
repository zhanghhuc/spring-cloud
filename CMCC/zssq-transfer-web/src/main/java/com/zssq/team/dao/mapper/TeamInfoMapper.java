package com.zssq.team.dao.mapper;

import java.util.List;

import com.zssq.team.pojo.TeamInfo;

public interface TeamInfoMapper {
    

    /**
     * @Function selectData
     * @Description 查询
     * @return
     */
    List<TeamInfo> selectData();
    
    
    /**
     * @Function batchInsert
     * @Description 批量
     * @param teamInfo
     * @return
     */
    int batchInsert(List<TeamInfo> teamInfo);
    
    
    /**
     * @Function deleteTeamInfo
     * @Description 删除
     * @return
     */
    int deleteTeamInfo();
    
    /**
     * @Function selectHonotTeam
     * @Description 查询百强
     * @return
     */
    List<String> selectHonotTeam();
    
    /**
     * @Function selectAOrg
     * @Description 查询集团组织编码
     * @return
     */
    List<String> selectAOrg();
    
    /**
     * @Function updateTeamIcon
     * @Description 变更班组头像(null)
     * @return
     */
    int updateTeamIcon();

}