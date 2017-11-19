package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.SysSkinInfo;

public interface SysSkinInfoMapper {
	
    List<SysSkinInfo> selectPage(SysSkinInfo record);
    
    int selectCount(SysSkinInfo record);
    
    List<SysSkinInfo> selectInUse();
    
    int updateInUse(Long id);
    
    int updateNotInUse(Long id);
}