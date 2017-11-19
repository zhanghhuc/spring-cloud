package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VoteCollection;
import com.zssq.dao.pojo.VoteCollectionExample;
import com.zssq.dao.pojo.VoteInfo;

public interface VoteCollectionMapper {
    int countByExample(VoteCollectionExample example);

    int deleteByExample(VoteCollectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteCollection record);

    int insertSelective(VoteCollection record);

    List<VoteCollection> selectByExample(VoteCollectionExample example);

    VoteCollection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteCollection record, @Param("example") VoteCollectionExample example);

    int updateByExample(@Param("record") VoteCollection record, @Param("example") VoteCollectionExample example);

    int updateByPrimaryKeySelective(VoteCollection record);

    int updateByPrimaryKey(VoteCollection record);

    /**
     * @Function deleteVoteCollection
     * @Description 删除收藏记录
     * @param paramMap
     * @return
     */
	int deleteVoteCollection(Map<String, Object> paramMap);

	/**
	 * @Function selectCollectionList
	 * @Description 查询我的收藏列表
	 * @param paramMap
	 * @return
	 */
	List<VoteInfo> selectCollectionList(Map<String, Object> paramMap);
}