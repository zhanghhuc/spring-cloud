package com.zssq.dao.mapper;

import com.zssq.dao.pojo.UserShieldRelation;

public interface UserShieldRelationMapper {
	
	
    int deleteByUserCode(UserShieldRelation record);
    
    int insert(UserShieldRelation record);

    int selectCountByUserCode(UserShieldRelation record);

}