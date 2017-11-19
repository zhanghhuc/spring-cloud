package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.SSO;

public interface SSOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SSO record);

    int insertSelective(SSO record);

    SSO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SSO record);

    int updateByPrimaryKey(SSO record);
    
    List<SSO> selectAll(SSO record);
    
    List<SSO> selectInfo();
    
    int updateBatch(List<SSO> record);
}