package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ActivityCollection;
import com.zssq.dao.pojo.ActivityCollectionExample;

public interface ActivityCollectionMapper {
    int countByExample(ActivityCollectionExample example);

    int deleteByExample(ActivityCollectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityCollection record);

    int insertSelective(ActivityCollection record);

    List<ActivityCollection> selectByExample(ActivityCollectionExample example);

    ActivityCollection selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityCollection record, @Param("example") ActivityCollectionExample example);

    int updateByExample(@Param("record") ActivityCollection record, @Param("example") ActivityCollectionExample example);

    int updateByPrimaryKeySelective(ActivityCollection record);

    int updateByPrimaryKey(ActivityCollection record);
}