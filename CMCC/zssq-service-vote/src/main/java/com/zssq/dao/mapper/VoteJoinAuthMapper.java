package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VoteJoinAuth;
import com.zssq.dao.pojo.VoteJoinAuthExample;

public interface VoteJoinAuthMapper {
    int countByExample(VoteJoinAuthExample example);

    int deleteByExample(VoteJoinAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteJoinAuth record);

    int insertSelective(VoteJoinAuth record);

    List<VoteJoinAuth> selectByExample(VoteJoinAuthExample example);

    VoteJoinAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteJoinAuth record, @Param("example") VoteJoinAuthExample example);

    int updateByExample(@Param("record") VoteJoinAuth record, @Param("example") VoteJoinAuthExample example);

    int updateByPrimaryKeySelective(VoteJoinAuth record);

    int updateByPrimaryKey(VoteJoinAuth record);

    /**
     * @Function deleteAuthInfo
     * @Description 删除参与权限信息
     * @param paramMap
     */
	void deleteAuthInfo(Map<String, Object> paramMap);
}