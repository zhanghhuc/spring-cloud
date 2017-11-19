package com.zssq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.UserMsgCommentMapper;
import com.zssq.dao.pojo.UserMsgComment;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageCommentAddModel;
import com.zssq.model.MessageCommentModel;
import com.zssq.service.MessageCommentService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;


@Service("messageCommentService")
public class MessageCommentServiceImpl implements MessageCommentService {

	
	@Autowired
	private UserMsgCommentMapper userMsgCommentMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	
	/**
	 * 
	 * @Title: getMsgCommentList  
	 * @Description: 评论查询
	 * @param messageCommentModel
	 * @return    参数  
	 * @return: UserMsgComment    返回类型
	 */
	
	@Override
	public PageBean getMsgCommentList(MessageCommentModel messageCommentModel)throws BusinessException {
		redisUtil.delMsgNumber(messageCommentModel.getUserCode(), 2);
		
		PageBean pageBean=new PageBean();
		
		//分页计算
		messageCommentModel.setPageNo(messageCommentModel.getPageSize()*messageCommentModel.getPageNo());		
		
		pageBean.setRecordList(userMsgCommentMapper.selectPage(messageCommentModel));
		pageBean.setTotalCount(userMsgCommentMapper.selectCount(messageCommentModel));
		
		return pageBean;
		
	}


	/**
	 * 
	 * @Title: addMsgComment  
	 * @Description: 添加评论消息
	 * @param MsgCommentAddModel
	 * @return    参数  
	 * @return: int    返回类型
	 */
	@Override
	public int addMsgComment(MessageCommentAddModel msgCommentAddModel)throws BusinessException {
		
		UserMsgComment  userMsgComment=new UserMsgComment();
		userMsgComment.setCreateTime(DateUtils.getTime());
		userMsgComment.setIsDelete((byte)0);
		userMsgComment.setMsgCode(UUIDHelper.getUUID());
		
		userMsgComment.setCommentCode(msgCommentAddModel.getCommentCode());
		userMsgComment.setCommentUserCode(msgCommentAddModel.getCommentUserCode());
		userMsgComment.setOrgCode(msgCommentAddModel.getOrgCode());
		//userMsgComment.setOrgLevel((byte)msgCommentAddModel.getOrgLeve());
		userMsgComment.setOriginalCode(msgCommentAddModel.getOriginalCode());
		userMsgComment.setOriginalContent(msgCommentAddModel.getOriginalContent());
		userMsgComment.setTenantCode(msgCommentAddModel.getTenantCode());
		userMsgComment.setCommetType((byte)msgCommentAddModel.getType());
		userMsgComment.setUserCode(msgCommentAddModel.getUserCode());
		userMsgComment.setCommentcontent(msgCommentAddModel.getCommentContent());
		
		return userMsgCommentMapper.insert(userMsgComment);
	}

	

	/**
	 * 
	 * @Title: delMsgComment  
	 * @Description: 删除评论消息
	 * @param commentCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delMsgComment(String commentCode) throws BusinessException {
		UserMsgComment  userMsgComment=new UserMsgComment();
		userMsgComment.setMsgCode(commentCode);
		userMsgComment.setIsDelete((byte)1);
		int state=userMsgCommentMapper.delComment(userMsgComment);
		return state==0?false:true;
	}
	
}
