package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.TeamElect;

public interface TeamElectMapper {
	
	/** 动态插入 */
    int insertSelective(TeamElect record);

    /** 动态插入 */
    int closeElect();
    
    /** 按条件查询(分页) */
    List<TeamElect> selectByRecord(TeamElect record);
    
    /** 查询总数 */
    int selectCount(TeamElect record);
    
    /** 0:历史评选 1:上一次评选 2正在进行的评选(0-->1) */
    int lastToHis();
    
    /** 0:历史评选 1:上一次评选 2正在进行的评选(2-->1) */
    int nowToLast();
}