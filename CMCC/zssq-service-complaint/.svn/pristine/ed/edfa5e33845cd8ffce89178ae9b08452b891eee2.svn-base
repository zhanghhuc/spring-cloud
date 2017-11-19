package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ComplaintHandlerRecord;
import com.zssq.dao.pojo.ComplaintHandlerRecordExample;

public interface ComplaintHandlerRecordMapper {
    int countByExample(ComplaintHandlerRecordExample example);

    int deleteByExample(ComplaintHandlerRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ComplaintHandlerRecord record);

    int insertSelective(ComplaintHandlerRecord record);

    List<ComplaintHandlerRecord> selectByExample(ComplaintHandlerRecordExample example);

    ComplaintHandlerRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ComplaintHandlerRecord record, @Param("example") ComplaintHandlerRecordExample example);

    int updateByExample(@Param("record") ComplaintHandlerRecord record, @Param("example") ComplaintHandlerRecordExample example);

    int updateByPrimaryKeySelective(ComplaintHandlerRecord record);

    int updateByPrimaryKey(ComplaintHandlerRecord record);
}