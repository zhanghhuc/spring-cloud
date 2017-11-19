package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.VoteReview;
import com.zssq.dao.pojo.VoteReviewExample;

public interface VoteReviewMapper {
    int countByExample(VoteReviewExample example);

    int deleteByExample(VoteReviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoteReview record);

    int insertSelective(VoteReview record);

    List<VoteReview> selectByExample(VoteReviewExample example);

    VoteReview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoteReview record, @Param("example") VoteReviewExample example);

    int updateByExample(@Param("record") VoteReview record, @Param("example") VoteReviewExample example);

    int updateByPrimaryKeySelective(VoteReview record);

    int updateByPrimaryKey(VoteReview record);
}