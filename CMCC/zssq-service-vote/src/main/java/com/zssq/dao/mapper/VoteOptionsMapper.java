package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VoteOptions;
import com.zssq.dao.pojo.VoteOptionsExample;

public interface VoteOptionsMapper {
    int countByExample(VoteOptionsExample example);

    int deleteByExample(VoteOptionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteOptions record);

    int insertSelective(VoteOptions record);

    List<VoteOptions> selectByExample(VoteOptionsExample example);

    VoteOptions selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteOptions record, @Param("example") VoteOptionsExample example);

    int updateByExample(@Param("record") VoteOptions record, @Param("example") VoteOptionsExample example);

    int updateByPrimaryKeySelective(VoteOptions record);

    int updateByPrimaryKey(VoteOptions record);
    
    /**
     * 批量插入投票选项
     * @param options 选项集合
     * @return
     */
    int batchInsertOptions(List<VoteOptions> options);

    /**
     * 批量为选中的选项人数加一
     * @param tenantCode
     * @param infoCode
     * @param currentTime
     * @param optOrders
     */
	void batchUpdateSelectedNum(Map<String,Object> map);

	/**
	 * SUM所有选项被选择人数
	 * @Function countTotalSelectedNum
	 * @Description 
	 * @param voteInfoCode
	 * @return
	 */
	int countTotalSelectedNum(String voteInfoCode);	
	
	/**
	 * @Function deleteOptions
	 * @Description 删除选项信息
	 * @param paramMap
	 */
	void deleteOptions(Map<String, Object> paramMap);

	/**
	 * 查询投票的选项
	 * @param code
	 * @return
	 */
	List<VoteOptions> selectVoteOptions(String code);

}