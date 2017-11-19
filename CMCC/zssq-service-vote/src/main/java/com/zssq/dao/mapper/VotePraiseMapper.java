package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VotePraise;
import com.zssq.dao.pojo.VotePraiseExample;

public interface VotePraiseMapper {
    int countByExample(VotePraiseExample example);

    int deleteByExample(VotePraiseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VotePraise record);

    int insertSelective(VotePraise record);

    List<VotePraise> selectByExample(VotePraiseExample example);

    VotePraise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VotePraise record, @Param("example") VotePraiseExample example);

    int updateByExample(@Param("record") VotePraise record, @Param("example") VotePraiseExample example);

    int updateByPrimaryKeySelective(VotePraise record);

    int updateByPrimaryKey(VotePraise record);

    /**
     * @Function deleteVotePraise
     * @Description   删除点赞记录
     * @param delMap  过滤条件
     * @return
     */
	int deleteVotePraise(Map<String, Object> delMap);
}