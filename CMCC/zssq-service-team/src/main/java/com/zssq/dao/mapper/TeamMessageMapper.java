package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.TeamMessage;
import com.zssq.dao.vo.MessageGPSVo;

public interface TeamMessageMapper {
    
	/** 根据唯一标识删除 */
	int deleteByCode(String code);

	/** 动态插入 */
    int insertSelective(TeamMessage record);

    /** 根据唯一标识查询 */
    TeamMessage selectByCode(String code);
    
    /** 查询班组留言 */
    List<TeamMessage> selectPage(TeamMessage record);
    
    /** 查询班组留言(总数)*/
    int selectCount(TeamMessage record);
    
    /** 举报/取消举报 */
    int complaintMessage(TeamMessage record);
    
    /**
     * 查询班组留言集合，并过滤掉指定的留言
     * 
     * @param record
     * 			封装查询条件
     * @return 班组留言信息集合
     */
    List<TeamMessage> selectTeamMessageGPS(MessageGPSVo messageGPSVo);
    
    /**
     * 查询班组留言集合，并过滤掉指定的留言，数据量统计
     * 
     * @param messageGPSVo
     * 			封装查询条件
     * @return 记录数
     */
    int selectTeamMessageGPSCount(MessageGPSVo messageGPSVo);
    
}