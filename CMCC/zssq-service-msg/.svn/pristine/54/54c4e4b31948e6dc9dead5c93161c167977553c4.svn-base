package com.zssq.dao.mapper;

import java.util.List;

import com.zssq.dao.pojo.MsgPrivateLetter;
import com.zssq.model.DelPrivateLetterVO;
import com.zssq.model.MessagePrivateInfoModel;
import com.zssq.model.MessagePrivateLetterModel;

public interface MsgPrivateLetterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgPrivateLetter record);

    int insertSelective(MsgPrivateLetter record);

    MsgPrivateLetter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgPrivateLetter record);

    int updateByPrimaryKeyWithBLOBs(MsgPrivateLetter record);

    int updateByPrimaryKey(MsgPrivateLetter record);
    
    /**
     * 
     * @Title: selectPageMain  
     * @Description: 私信主列表查询
     * @param letterModel
     * @return    参数  
     * @return: List<MsgPrivateLetter>    返回类型
     */
    List<MsgPrivateLetter> selectPageMain(MessagePrivateLetterModel letterModel);
    
    /**
     * 
     * @Title: selectCountMain  
     * @Description: 私信主列表查询
     * @param letterModel
     * @return    参数  
     * @return: int    返回类型
     */
    int selectCountMain(MessagePrivateLetterModel letterModel);
    
    
    /**
     * 
     * @Title: selectPageMain  
     * @Description: 私信详细列表查询
     * @param letterModel
     * @return    参数  
     * @return: List<MsgPrivateLetter>    返回类型
     */
    List<MsgPrivateLetter> selectPageInfo(MessagePrivateInfoModel letterModel);
    
    /**
     * 
     * @Title: selectCountMain  
     * @Description: 私信详细列表查询
     * @param letterModel
     * @return    参数  
     * @return: int    返回类型
     */
    int selectCountInfo(MessagePrivateInfoModel letterModel);
    
    
    
    
    /**
     * 
     * @Title: delPrivateLetter  
     * @Description: TODO
     * @param userCode
     * @return    参数  
     * @return: int    返回类型
     */
    int delPrivateLetter(DelPrivateLetterVO record);
    
    
    
}