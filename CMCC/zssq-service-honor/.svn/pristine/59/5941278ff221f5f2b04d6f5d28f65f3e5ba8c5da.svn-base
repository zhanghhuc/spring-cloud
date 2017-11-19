package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zssq.dao.pojo.HonorDefinition;
import com.zssq.dao.pojo.HonorDefinitionExample;

@Repository
public interface HonorDefinitionMapper {
    int countByExample(HonorDefinitionExample example);

    int deleteByExample(HonorDefinitionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HonorDefinition record);

    int insertSelective(HonorDefinition record);

    List<HonorDefinition> selectByExample(HonorDefinitionExample example);

    HonorDefinition selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HonorDefinition record, @Param("example") HonorDefinitionExample example);

    int updateByExample(@Param("record") HonorDefinition record, @Param("example") HonorDefinitionExample example);

    int updateByPrimaryKeySelective(HonorDefinition record);

    int updateByPrimaryKey(HonorDefinition record);
}