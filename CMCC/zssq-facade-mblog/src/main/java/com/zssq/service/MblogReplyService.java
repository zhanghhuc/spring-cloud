package com.zssq.service;

import com.zssq.dao.pojo.MblogReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.qo.ReplyInfoQO;
import com.zssq.utils.PageBean;

/**
 * 
    * @ClassName: MblogReplyService  
    * @Description: 微博回复Service  
    * @author Mr.B  
    * @date 2017年3月20日  
    *
 */
public interface MblogReplyService {

	/**
	 * 
	    * @Title: addReplyInfo  
	    * @Description: 添加回复信息
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	public boolean addReplyInfo(ReplyInfoQO qo)throws BusinessException;
	
	/**
	 * 
	    * @Title: queryReplyList  
	    * @Description: 查询分页数据
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	public PageBean queryReplyList(ReplyInfoQO qo)throws BusinessException;
	
	/**
	 * 
	    * @Title: addOrDelReplyPraise  
	    * @Description: 点赞/取消点赞
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	public boolean addOrDelReplyPraise(ReplyInfoQO qo)throws BusinessException;
	
	/**
	 * 
	    * @Title: deleteReplyInfo  
	    * @Description: 删除回复
	    * @param qo
	    * @throws BusinessException
		* @return boolean    返回类型
	 */
	public boolean deleteReplyInfo(ReplyInfoQO qo)throws BusinessException;
	
	/**
	 * 
	    * @Title: gotoReplyList  
	    * @Description: 定位到回复列表
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	public PageBean gotoReplyList(ReplyInfoQO qo)throws BusinessException;

	/**
	 * 
	    * @Title: gotoReplyListByPage  
	    * @Description: 定位回复列表可分页
	    * @param qo
	    * @throws BusinessException
		* @return PageBean    返回类型
	 */
	public PageBean gotoReplyListByPage(ReplyInfoQO qo)throws BusinessException;

	/**
	 * 
	    * @Title: getReplyInfo  
	    * @Description: 获取回复信息
	    * @param replyCode 	回复CODE
	    * @throws BusinessException
		* @return MblogReply    返回类型
	 */
	public MblogReply getReplyInfo(String replyCode)throws BusinessException ;
}