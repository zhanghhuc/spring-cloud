package com.zssq.disk.dao;

import com.zssq.disk.po.DiskFile;
import com.zssq.disk.po.DiskFileExample;

import java.util.List;

public interface DiskFileMapper {
    int countByExample(DiskFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DiskFile record);

    int insertSelective(DiskFile record);

    List<DiskFile> selectByExample(DiskFileExample example);

    DiskFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DiskFile record);

    int updateByPrimaryKey(DiskFile record);

    boolean insertBatch(List<DiskFile> diskFiles);
}