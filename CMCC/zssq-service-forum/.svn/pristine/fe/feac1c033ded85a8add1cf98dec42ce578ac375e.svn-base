package com.zssq.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zssq.dao.pojo.ForumPlatesMember;
import com.zssq.dao.pojo.ForumPlatesMemberExample;

public interface ForumPlatesMemberMapper {
    int countByExample(ForumPlatesMemberExample example);

    int deleteByExample(ForumPlatesMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumPlatesMember record);

    int insertSelective(ForumPlatesMember record);

    List<ForumPlatesMember> selectByExample(ForumPlatesMemberExample example);

    ForumPlatesMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ForumPlatesMember record, @Param("example") ForumPlatesMemberExample example);

    int updateByExample(@Param("record") ForumPlatesMember record, @Param("example") ForumPlatesMemberExample example);

    int updateByPrimaryKeySelective(ForumPlatesMember record);

    int updateByPrimaryKey(ForumPlatesMember record);
    /**
     * 根据租户标识、论坛所属CODE、用户CODE精确查询某一版块下的用户信息
     * @Function queryOneMemberWithCondition
     * @Description 
     * @param tenantCode 租户标识
     * @param belongCode 论坛所属CODE
     * @param memberCode 用户CODE
     * @return
     */
	ForumPlatesMember queryOneMemberWithBelongCode(@Param("tenantCode") String tenantCode,
			@Param("belongCode") String belongCode, @Param("memberCode") String memberCode);
    /**
     * 根据租户标识、论坛版块CODE、用户CODE精确查询某一版块下的用户信息
     * @Function queryOneMemberUnion
     * @Description 
     * @param tenantCode 租户标识
     * @param platesCode 论坛版块CODE
     * @param memberCode 用户CODE
     * @return
     */
	ForumPlatesMember queryOneMemberWithPlatesCode(@Param("tenantCode") String tenantCode,
			@Param("platesCode") String platesCode, @Param("memberCode") String memberCode);
    
    
}