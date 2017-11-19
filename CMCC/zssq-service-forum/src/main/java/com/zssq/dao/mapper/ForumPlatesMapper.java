package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ForumPlates;
import com.zssq.dao.pojo.ForumPlatesExample;

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

    /**
     * 根据论坛code查询班组code
     * @param plateCode
     * @return
     */
	String queryTeamCode(String plateCode);
}