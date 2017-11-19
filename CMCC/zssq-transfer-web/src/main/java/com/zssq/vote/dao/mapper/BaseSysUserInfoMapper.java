package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.BaseSysUserInfo;
import com.zssq.vote.pojo.BaseSysUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseSysUserInfoMapper {
    int countByExample(BaseSysUserInfoExample example);

    int deleteByExample(BaseSysUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseSysUserInfo record);

    int insertSelective(BaseSysUserInfo record);

    List<BaseSysUserInfo> selectByExample(BaseSysUserInfoExample example);

    BaseSysUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseSysUserInfo record, @Param("example") BaseSysUserInfoExample example);

    int updateByExample(@Param("record") BaseSysUserInfo record, @Param("example") BaseSysUserInfoExample example);

    int updateByPrimaryKeySelective(BaseSysUserInfo record);

    int updateByPrimaryKey(BaseSysUserInfo record);
}