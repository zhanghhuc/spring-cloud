package com.zssq.disk.dao;


import com.zssq.disk.po.NetdiskFile;
import com.zssq.disk.po.NetdiskFileExample;

import java.util.List;

public interface NetdiskFileMapper {
    int countByExample(NetdiskFileExample example);

    int insert(NetdiskFile record);

    int insertSelective(NetdiskFile record);

    List<NetdiskFile> selectByExample(NetdiskFileExample example);

    List<NetdiskFile> selectList(NetdiskFileExample example);
}