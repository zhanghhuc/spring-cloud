package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.SaasTenantInfo;

public interface SaasTenantInfoMapper {

    int insertSelective(SaasTenantInfo record);
    
    List<SaasTenantInfo> selectTenant();
    
    int setTenantState(SaasTenantInfo record);

}