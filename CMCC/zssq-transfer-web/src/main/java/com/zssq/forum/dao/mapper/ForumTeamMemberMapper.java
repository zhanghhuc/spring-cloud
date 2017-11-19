package com.zssq.forum.dao.mapper;

import com.zssq.forum.pojo.ForumTeamMember;
import com.zssq.forum.pojo.ForumTeamMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForumTeamMemberMapper {
    int countByExample(ForumTeamMemberExample example);

    int deleteByExample(ForumTeamMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumTeamMember record);

    int insertSelective(ForumTeamMember record);

    List<ForumTeamMember> selectByExample(ForumTeamMemberExample example);

    ForumTeamMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumTeamMember record, @Param("example") ForumTeamMemberExample example);

    int updateByExample(@Param("record") ForumTeamMember record, @Param("example") ForumTeamMemberExample example);

    int updateByPrimaryKeySelective(ForumTeamMember record);

    int updateByPrimaryKey(ForumTeamMember record);
}