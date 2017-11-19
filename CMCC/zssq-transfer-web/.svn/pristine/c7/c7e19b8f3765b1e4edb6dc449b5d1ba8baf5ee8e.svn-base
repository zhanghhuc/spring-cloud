package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.BaseSysOrgInfo;
import com.zssq.vote.pojo.BaseSysOrgInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseSysOrgInfoMapper {
    int countByExample(BaseSysOrgInfoExample example);

    int deleteByExample(BaseSysOrgInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseSysOrgInfo record);

    int insertSelective(BaseSysOrgInfo record);

    List<BaseSysOrgInfo> selectByExample(BaseSysOrgInfoExample example);

    BaseSysOrgInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseSysOrgInfo record, @Param("example") BaseSysOrgInfoExample example);

    int updateByExample(@Param("record") BaseSysOrgInfo record, @Param("example") BaseSysOrgInfoExample example);

    int updateByPrimaryKeySelective(BaseSysOrgInfo record);

    int updateByPrimaryKey(BaseSysOrgInfo record);
    /**
     * 根据orgId查询SysOrgInfo
     * @Function queryOrgInfoByOrgId
     * @Description 
     * @param orgId
     * @return
     */
    BaseSysOrgInfo queryOrgInfoByOrgId(int orgId);
    /**
     * 查询集团信息
     * @Function queryGroupInfo
     * @Description 
     * @param srcCode
     * @return
     */
    BaseSysOrgInfo queryGroupInfo(String srcCode);
}