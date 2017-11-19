package com.zssq.forum.dao.mapper;

import com.zssq.forum.pojo.ForumPlates;
import com.zssq.forum.pojo.ForumPlatesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForumPlatesMapper {
    int countByExample(ForumPlatesExample example);

    int deleteByExample(ForumPlatesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumPlates record);

    int insertSelective(ForumPlates record);

    List<ForumPlates> selectByExample(ForumPlatesExample example);

    ForumPlates selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumPlates record, @Param("example") ForumPlatesExample example);

    int updateByExample(@Param("record") ForumPlates record, @Param("example") ForumPlatesExample example);

    int updateByPrimaryKeySelective(ForumPlates record);

    int updateByPrimaryKey(ForumPlates record);
}