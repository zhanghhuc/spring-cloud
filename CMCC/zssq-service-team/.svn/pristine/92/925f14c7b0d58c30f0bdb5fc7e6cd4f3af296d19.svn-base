package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.TeamRecommend;

public interface TeamRecommendMapper {

	/** 动态插入 */
    int insertSelective(TeamRecommend record);

    /** 删除百强班组 */
    int deleteHonorTeam(TeamRecommend record);
    
    /** 颁发荣誉 */
    int awardHonor(TeamRecommend record);
    
    /** 按条件查询 */
    List<TeamRecommend> selectByRecord(TeamRecommend record);
    
    /** 根据评选拜编码查询班组 */
    List<String> selectTeamCodeByElect(String electCode);
}