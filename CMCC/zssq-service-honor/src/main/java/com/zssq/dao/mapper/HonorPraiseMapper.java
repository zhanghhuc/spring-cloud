package com.zssq.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zssq.dao.pojo.HonorPraise;
import com.zssq.dao.pojo.HonorPraiseExample;

@Repository
public interface HonorPraiseMapper {
    int countByExample(HonorPraiseExample example);

    int deleteByExample(HonorPraiseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HonorPraise record);

    int insertSelective(HonorPraise record);

    List<HonorPraise> selectByExample(HonorPraiseExample example);

    HonorPraise selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HonorPraise record, @Param("example") HonorPraiseExample example);

    int updateByExample(@Param("record") HonorPraise record, @Param("example") HonorPraiseExample example);

    int updateByPrimaryKeySelective(HonorPraise record);

    int updateByPrimaryKey(HonorPraise record);

    /**
     * @Function deleteHonorPraise
     * @Description 删除点赞记录
     * @param delMap
     * @return int
     */
	int deleteHonorPraise(Map<String, Object> delMap);
}