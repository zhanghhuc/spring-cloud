package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.HonorTeam;
import com.zssq.dao.pojo.TeamCourse;

public interface TeamCourseMapper {

	/** 动态插入 */
    int insertSelective(TeamCourse record);
    
    /** 变更推荐标识 */
    int updateByCode(TeamCourse record);

    /** 根据条件查询班组推荐历程 */
    List<TeamCourse> selectByRecord(TeamCourse record);

    /** 查询历史百强班组 */
    List<HonorTeam> selectHonorTeamHisPage(TeamCourse record);
    
    /** 查询历史百强班组(总数) */
    int selectHonorTeamHisCount(TeamCourse record);
    
    /** 在历程中删除百强班组  */
    int deleteHonorTeam(TeamCourse record);
    
    /** 查询上一次评选的百强班组,用于推送 */
    List<HonorTeam> selectLastHonorTeam(String electCode);
}