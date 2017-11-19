package com.zssq.filing.dao.mapper;

import java.util.List;
import java.util.Map;

import com.zssq.filing.pojo.KcFriendGrpUsrRel;

public interface KcFriendGrpUsrRelMapper {
    int insert(KcFriendGrpUsrRel record);

    int insertSelective(KcFriendGrpUsrRel record);

    /**
     * 
     * @Function selectCount
     * @Description 查询好友记录数
     * @param status 0:关注，1：好友
     * @return
     */
	Integer selectCount(Byte status);

	/**
	 * 
	 * @Function selectPage
	 * @Description 查询好友
	 * @param param
	 * @return
	 */
	List<KcFriendGrpUsrRel> selectPage(Map<String, Object> param);

}