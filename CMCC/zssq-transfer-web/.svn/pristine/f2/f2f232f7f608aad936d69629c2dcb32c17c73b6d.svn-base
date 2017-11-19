package com.zssq.vote.dao.mapper;

import com.zssq.vote.pojo.BaseTeamInfo;
import com.zssq.vote.pojo.BaseTeamInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseTeamInfoMapper {
    int countByExample(BaseTeamInfoExample example);

    int deleteByExample(BaseTeamInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BaseTeamInfo record);

    int insertSelective(BaseTeamInfo record);

    List<BaseTeamInfo> selectByExample(BaseTeamInfoExample example);

    BaseTeamInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaseTeamInfo record, @Param("example") BaseTeamInfoExample example);

    int updateByExample(@Param("record") BaseTeamInfo record, @Param("example") BaseTeamInfoExample example);

    int updateByPrimaryKeySelective(BaseTeamInfo record);

    int updateByPrimaryKey(BaseTeamInfo record);
    /**
     * 通过班组ID查询班组信息
     * @Function queryTeamInfoByTeamId
     * @Description 
     * @param teamId
     * @return
     */
    BaseTeamInfo queryTeamInfoByTeamId(Integer teamId);
}