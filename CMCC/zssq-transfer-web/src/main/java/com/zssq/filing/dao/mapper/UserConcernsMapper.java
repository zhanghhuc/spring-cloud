package com.zssq.filing.dao.mapper;

import java.util.List;

import com.zssq.filing.pojo.UserConcerns;

public interface UserConcernsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserConcerns record);

    int insertSelective(UserConcerns record);

    UserConcerns selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserConcerns record);

    int updateByPrimaryKey(UserConcerns record);

    /**
     * 
     * @Function bathInsert
     * @Description 批量新增
     * @param userConcernsList
     */
	void bathInsert(List<UserConcerns> userConcernsList);
}