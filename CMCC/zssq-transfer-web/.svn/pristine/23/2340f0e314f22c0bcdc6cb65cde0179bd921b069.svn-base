package com.zssq.forum.dao.mapper;

import com.zssq.forum.pojo.ForumPlatesMember;
import com.zssq.forum.pojo.ForumPlatesMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForumPlatesMemberMapper {
    int countByExample(ForumPlatesMemberExample example);

    int deleteByExample(ForumPlatesMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumPlatesMember record);

    int insertSelective(ForumPlatesMember record);

    List<ForumPlatesMember> selectByExample(ForumPlatesMemberExample example);

    ForumPlatesMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumPlatesMember record, @Param("example") ForumPlatesMemberExample example);

    int updateByExample(@Param("record") ForumPlatesMember record, @Param("example") ForumPlatesMemberExample example);

    int updateByPrimaryKeySelective(ForumPlatesMember record);

    int updateByPrimaryKey(ForumPlatesMember record);
}