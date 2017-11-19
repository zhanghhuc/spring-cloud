package com.zssq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.dao.mapper.UserMsgBoardReplyMapper;
import com.zssq.dao.pojo.UserMsgBoardReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageBoardReplyModel;
import com.zssq.service.MessagerBoradReplyService;
import com.zssq.utils.PageBean;

@Service("messagerBoradReplyService")
public class MessagerBoradReplyServiceImpl implements MessagerBoradReplyService{

	@Autowired
	private UserMsgBoardReplyMapper userMsgBoardReplyMapper;

	
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
	@Override
	public PageBean getBoardReplyList(String id,String boardCode,Integer pageSize) throws BusinessException{
		
		PageBean pageBean=new PageBean();
		MessageBoardReplyModel model=new MessageBoardReplyModel();
		
		model.setBoardCode(boardCode);
		model.setId(id);
		model.setPageSize(pageSize);
	/*	model.setPageNo(pageSize*pageNo);
		model.setPageSize(pageSize);
		model.setBoardCode(boardCode);*/
		List<UserMsgBoardReply> list=userMsgBoardReplyMapper.selectPage(model);
		int total=userMsgBoardReplyMapper.selectCount(model);
		
		pageBean.setTotalCount(total);
		pageBean.setRecordList(list);
		
		return pageBean;
	}


	/**
	 * 
	 * @Title: delBoardReply  
	 * @Description: 删除code
	 * @param replyCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delBoardReply(String replyCode) throws BusinessException {
		
		UserMsgBoardReply record=new UserMsgBoardReply();
		record.setIsDelete((byte)1);
		record.setReplyCode(replyCode);
		int state=userMsgBoardReplyMapper.delBoardReply(record);
		
		return state==1?true:false;
	}

}
