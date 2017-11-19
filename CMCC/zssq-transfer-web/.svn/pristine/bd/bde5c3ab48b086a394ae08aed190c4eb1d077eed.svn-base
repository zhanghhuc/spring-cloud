package com.zssq.forum.dao.mapper;

import com.zssq.forum.pojo.ForumTeamInfo;
import com.zssq.forum.pojo.ForumTeamInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ForumTeamInfoMapper {
    int countByExample(ForumTeamInfoExample example);

    int deleteByExample(ForumTeamInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumTeamInfo record);

    int insertSelective(ForumTeamInfo record);

    List<ForumTeamInfo> selectByExample(ForumTeamInfoExample example);

    ForumTeamInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumTeamInfo record, @Param("example") ForumTeamInfoExample example);

    int updateByExample(@Param("record") ForumTeamInfo record, @Param("example") ForumTeamInfoExample example);

    int updateByPrimaryKeySelective(ForumTeamInfo record);

    int updateByPrimaryKey(ForumTeamInfo record);

    /**
     * @Function queryOriginalForum
     * @Description 查询论坛班组主表信息
     * @param paramMap
     * @return
     */
	List<Map<String, Object>> queryOriginalForum(Map<String, Object> paramMap);
}