package com.zssq.disk.dao;

import com.zssq.news.po.SysOrgInfo;
import com.zssq.news.po.SysOrgInfoExample;

import java.util.List;

import static cfca.org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

public interface DiskSysOrgInfoMapper {
    int countByExample(SysOrgInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOrgInfo record);

    int insertSelective(SysOrgInfo record);

    List<SysOrgInfo> selectByExample(SysOrgInfoExample example);

    SysOrgInfo selectByPrimaryKey(Long id);

    SysOrgInfo selectByCode(String orgCode);

    int updateByPrimaryKeySelective(SysOrgInfo record);

    int updateByPrimaryKey(SysOrgInfo record);
}