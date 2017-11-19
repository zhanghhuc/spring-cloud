package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ComplaintInfo;
import com.zssq.dao.pojo.ComplaintInfoExample;

public interface ComplaintInfoMapper {
    int countByExample(ComplaintInfoExample example);

    int deleteByExample(ComplaintInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ComplaintInfo record);

    int insertSelective(ComplaintInfo record);

    List<ComplaintInfo> selectByExample(ComplaintInfoExample example);

    ComplaintInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ComplaintInfo record, @Param("example") ComplaintInfoExample example);

    int updateByExample(@Param("record") ComplaintInfo record, @Param("example") ComplaintInfoExample example);

    int updateByPrimaryKeySelective(ComplaintInfo record);

    int updateByPrimaryKey(ComplaintInfo record);

    /**
     * 查询某一举报人所举报的信息列表
     * @param map
     * @return
     */
	List<ComplaintInfo> selectComplaintInfoByPersonCode(Map<String, Object> map);
	
}