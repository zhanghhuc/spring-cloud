package com.zssq.service;

import com.zssq.exceptions.BusinessException;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessagerBoradReplyService  
 * @Description: 留言回复service  
 * @author YDB  
 * @date 2017年3月28日  
 *
 */
public interface MessagerBoradReplyService {
	
	
	/**
	 * 
	 * @Title: getBoardReplyList  
	 * @Description: 评论回复查询
	 * @param boardCode
	 * @param pageSize
	 * @param pageNo
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	//public PageBean getBoardReplyList(String boardCode,Integer pageSize,Integer pageNo) throws BusinessException;
	public PageBean getBoardReplyList(String id,String boardCode,Integer pageSize) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: delBoardReply  
	 * @Description: 删除code
	 * @param replyCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	public boolean delBoardReply(String replyCode) throws BusinessException;
	
}
