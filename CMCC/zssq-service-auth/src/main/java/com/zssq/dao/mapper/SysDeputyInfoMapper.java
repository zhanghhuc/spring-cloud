package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.dao.pojo.SysDeputyInfo;

public interface SysDeputyInfoMapper {

    int insertSelective(SysDeputyInfo record);

    SysDeputyInfo selectByCode(String deputyCode);
    
    List<SysDeputyInfo> selectByRecord(SysDeputyInfo record);
    
    /** 修改代发APP */
    int updateDeputyApps(SysDeputyInfo record);
    
    /** 根据code删除 */
    int deleteByCode(String deputyCode);
    
    /** 查询数据列表 */
    List<SysDeputyInfo> selectPage(Map<String,Object> map);
    
    /** 查询数据总条数 */
    int selectPageCount(Map<String,Object> map);

}