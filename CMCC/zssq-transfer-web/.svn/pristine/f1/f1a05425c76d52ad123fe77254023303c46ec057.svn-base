package com.zssq.filing.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.filing.pojo.SynFilterBase;

public interface SynFilterBaseMapper {
    int insert(SynFilterBase record);

    int insertSelective(SynFilterBase record);

    /**
     * 
     * @Function selectCount
     * @Description 查询记录总数
     * @return
     */
	Integer selectCount();
	
	/**
	 * 
	 * @Function selectPage
	 * @Description 分页查询记录数
	 * @param param
	 * @return
	 */
	List<SynFilterBase> selectPage(Map<String, Object> param);
}