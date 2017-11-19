package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.SysDictionary;

public interface SysDictionaryMapper {

    int insertSelective(SysDictionary record);

    SysDictionary selectByCode(String dictCode);
    
    List<SysDictionary> selectByRecord(SysDictionary record);

}