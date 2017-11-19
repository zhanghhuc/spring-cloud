package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.UserConcerns;

public interface UserConcernsMapper {

    int insertSelective(UserConcerns record);

    UserConcerns selectByCode(String concernsCode);
    
    int delUSerConcerns(UserConcerns record);
    
    int delBoth(UserConcerns record);
    
    List<UserConcerns> selectByRecord(UserConcerns record);
    
    int selectCount(UserConcerns record);
    
    int selectMyFansCount(String userCode);
    
    int selectMyConcernsCount(String userCode);
    
    List<String> selectMyConcerns(String userCode);
    
}