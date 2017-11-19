package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ComplaintMonitorInfo;
import com.zssq.dao.pojo.ComplaintMonitorInfoExample;

public interface ComplaintMonitorInfoMapper {
    int countByExample(ComplaintMonitorInfoExample example);

    int deleteByExample(ComplaintMonitorInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ComplaintMonitorInfo record);

    int insertSelective(ComplaintMonitorInfo record);

    List<ComplaintMonitorInfo> selectByExample(ComplaintMonitorInfoExample example);

    ComplaintMonitorInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ComplaintMonitorInfo record, @Param("example") ComplaintMonitorInfoExample example);

    int updateByExample(@Param("record") ComplaintMonitorInfo record, @Param("example") ComplaintMonitorInfoExample example);

    int updateByPrimaryKeySelective(ComplaintMonitorInfo record);

    int updateByPrimaryKey(ComplaintMonitorInfo record);
}