package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zssq.biz.MessageBoardBiz;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.mapper.UserBoardRemindMapper;
import com.zssq.dao.mapper.UserMsgBoardMapper;
import com.zssq.dao.mapper.UserMsgBoardReplyMapper;
import com.zssq.dao.pojo.UserBoardRemind;
import com.zssq.dao.pojo.UserMsgBoard;
import com.zssq.dao.pojo.UserMsgBoardReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.DelBoardMsgModel;
import com.zssq.model.GetReportBoradListModel;
import com.zssq.model.GetReportBoradReplyModel;
import com.zssq.model.ManagementBoardModel;
import com.zssq.model.MessageBoardReplyModel;
import com.zssq.model.MessageBoradModel;
import com.zssq.model.QueryBoardRemindModel;
import com.zssq.model.ReportBoardModel;
import com.zssq.model.ReporyReplyModel;
import com.zssq.service.MessagerBoradService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;


/**
 * 
 * @ClassName: MessagerBoradService  
 * @Description: 留言板  
 * @author YDB  
 * @date 2017年3月24日  
 *
 */

@Service("messagerBoradService")
public class MessagerBoradServiceImpl implements MessagerBoradService  {

	@Autowired
	private UserMsgBoardMapper userMsgBoardMapper;
	
	@Autowired
	private MessageBoardBiz messageBoardBiz;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private UserBoardRemindMapper boardRemind;
	
	
	@Autowired
	private UserMsgBoardReplyMapper userMsgBoardReplyMapper ;
	
	/**
	 * 
	 * @Title: getMyPublishMessageList  
	 * @Description: 获取留言墙列表
	 * @return    参数  
	 * @return: userMsgBoard    返回类型
	 */
	
	@Override
	public PageBean getMyPublishMessageList(MessageBoradModel msgBoradModel) throws BusinessException {
		redisUtil.delMsgNumber(msgBoradModel.getUserCode(), 4);
		
		return messageBoardBiz.getMyPublishMessageList(msgBoradModel);
	}

	/**
	 * 
	 * @Title: addMessageBoardInfo  
	 * @Description: 
	 * @param beMsgUserCode 被留言用户code
	 * @param userCode   留言用户code
	 * @param content    留言内容
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean addMessageBoardInfo(String msgCode,String beMsgUserCode, String userCode, String content,String orgCode,String tenantCode)throws BusinessException {
		UserMsgBoard userMsgBoard=new UserMsgBoard();
		Long date=DateUtils.getTime();
		userMsgBoard.setUserCode(beMsgUserCode);
		userMsgBoard.setCreateTime(date);
		userMsgBoard.setIsDelete((byte)0);
		userMsgBoard.setIsDeleteAll((byte)0);
		userMsgBoard.setMessageBoardCode(msgCode);
		userMsgBoard.setModifyTime(date);
		userMsgBoard.setOrgCode(orgCode);
	//	userMsgBoard.setOrgLevel((byte)Integer.parseInt(orgLevel));
		userMsgBoard.setTenantCode(tenantCode);
		userMsgBoard.setContent(content);
		userMsgBoard.setMessageBoardUserCode(userCode);
		userMsgBoard.setTenantCode(AuthConstants.TENANT_CODE);
		int state=userMsgBoardMapper.insert(userMsgBoard);
		
		
		//自己给自己留言不发送消息
		if(!userCode.equals(beMsgUserCode)){
			//添加消息
			UserBoardRemind remind=new UserBoardRemind();
			remind.setBoradCode(msgCode);
			remind.setCreateTime(date);
			remind.setIsDelete(0);
			remind.setMsgCode(UUIDHelper.getUUID());
			remind.setMsgType(1);
			remind.setTenantCode(tenantCode);
			remind.setOrgCode(orgCode);
			remind.setMsgContent(content);
			remind.setSendUserCode(userCode);
			remind.setTeamCode("");
			remind.setUserCode(beMsgUserCode);
			remind.setReceiveUserCode(beMsgUserCode);
			boardRemind.insert(remind);
			redisUtil.addNumber(beMsgUserCode, 4, 1);
		}
		
		
		
		return state ==1?true:false;
	}
	
	/**
	 * 
	 * @Title: delMsgBoard  
	 * @Description: 删除留言板(删除自己留言板中的留言)
	 * @param boardCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delMsgBoard(String boardCode) throws BusinessException {
		DelBoardMsgModel record=new DelBoardMsgModel();
		record.setMsgCode(boardCode);
		record.setModifyTime(DateUtils.getTime());
		
		int state=userMsgBoardMapper.delBoard(record);
		
		return state==1?true:false;
	}

	/**
	 * 
	 * @Title: delMyMsgBoard  
	 * @Description: 我的留言删除
	 * @param boardCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delMyMsgBoard(String boardCode) throws BusinessException {
		DelBoardMsgModel record=new DelBoardMsgModel();
		record.setMsgCode(boardCode);
		record.setModifyTime(DateUtils.getTime());
		int state=userMsgBoardMapper.delBoard(record);
		
		return state==1?true:false;
	}

	/**
	 * 
	 * @Title: addBzBoard  
	 * @Description: 班组添加 留言板
	 * @param board
	 * @throws BusinessException    参数  
	 * @return: void    返回类型
	 */
	
	@Override
	public void addBzBoard(UserBoardRemind remind) throws BusinessException {
		
		boardRemind.insert(remind);
		
		redisUtil.addNumber(remind.getUserCode(), 4, 1);
	}

	
	/**
	 * 
	 * @Title: addBoardReply  
	 * @Description:  添加留言回复
	 * @param reply
	 * @throws BusinessException    参数  
	 * @return: void    返回类型
	 */
	@Override
	public boolean addBoardReply(UserMsgBoardReply reply,String orgContent,String userCode) throws BusinessException {
		
		int state=userMsgBoardReplyMapper.insert(reply);
		
		//添加消息
		UserBoardRemind remind=new UserBoardRemind();
		remind.setCreateTime(DateUtils.getTime());
		remind.setIsDelete(0);
		remind.setMsgCode(UUIDHelper.getUUID());
		remind.setMsgType(1);
		remind.setOrgCode(reply.getOrgCode());
		remind.setMsgContent(reply.getReplyContent());
		remind.setOriginalContent(orgContent);
		remind.setTeamCode("");
		remind.setTenantCode(reply.getTenantCode());
		remind.setBoradCode(reply.getBoard());

		remind.setSendUserCode(reply.getUserCode());
		remind.setUserCode(reply.getUserCode());
		remind.setReceiveUserCode(reply.getReplyUserCode());
		boardRemind.insert(remind);
		
		UserBoardRemind remind2=new UserBoardRemind();
		remind2.setCreateTime(DateUtils.getTime());
		remind2.setIsDelete(0);
		remind2.setMsgCode(UUIDHelper.getUUID());
		remind2.setMsgType(1);
		remind2.setOrgCode(reply.getOrgCode());
		remind2.setMsgContent(reply.getReplyContent());
		remind2.setOriginalContent(orgContent);
		remind2.setTeamCode("");
		remind2.setTenantCode(reply.getTenantCode());
		remind2.setBoradCode(reply.getBoard());
		/*remind2.setUserCode(userCode);
		remind2.setReceiveUsercode(reply.getReplyUserCode());
		remind2.setSendUserCode(userCode);*/
		remind2.setSendUserCode(reply.getUserCode());
		remind2.setUserCode(reply.getReplyUserCode());
		remind2.setReceiveUserCode(reply.getReplyUserCode());
		
		
		boardRemind.insert(remind2);
		
		redisUtil.addNumber(reply.getUserCode(), 4, 1);
		
		return state==1;
	}

	
	
	/**
	 * 
	 * @Title: getBoardRemind  
	 * @Description: 获取留言板消息提醒
	 * @param userCode
	 * @param pageNo
	 * @param pageSize
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean getBoardRemind(String userCode, Integer pageNo, Integer pageSize)throws BusinessException {
		PageBean pageBean=new  PageBean();
		redisUtil.delMsgNumber(userCode, 4);
		QueryBoardRemindModel remind=new QueryBoardRemindModel();
		remind.setPageNo(pageNo*pageSize);
		remind.setPageSize(pageSize);
		remind.setUserCode(userCode);
		
		int count=boardRemind.selectCount(remind);
		
		List<UserBoardRemind> list=boardRemind.selectPage(remind);
		pageBean.setTotalCount(count);
		pageBean.setRecordList(list);
		
		return pageBean;
	}

	
	/**
	 * 
	 * @Title: delBoardRemind  
	 * @Description: 删除消息类型 
	 * @param msgCoude
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delBoardRemind(String msgCode)throws BusinessException {
		
		int state=boardRemind.delBoardRemind(msgCode);
		
		return state==1;
	}

	
	
	/**
	 * 
	 * @Title: ReportBoardAndReplyMsg  
	 * @Description: 留言，留言回复举报
	 * @param msgCode
	 * @param type 1-留言举报  2-回复举报
	 * @param delete
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean ReportBoardAndReplyMsg(String msgCode, int type, int delete) {
		int state=0;
		if(type==1){
			//留言操作
			ReportBoardModel record =new ReportBoardModel();
			record.setMsgCode(msgCode);
			record.setDelete(delete);
			record.setModifyTime(DateUtils.getTime());
			state=userMsgBoardMapper.reportBoard(record);
			return state==0;
			
		}else{
			//回复操作
			ReporyReplyModel record=new ReporyReplyModel();
			record.setDelete(delete);
			record.setMsgCode(msgCode);
			state=userMsgBoardReplyMapper.reportRely(record);
			return state==0;
		}
		
	}

	/**
	 * 
	 * @Title: ManagementBoardMsg  
	 * @Description: 后台管理个人留言板
	 * @param pageSize
	 * @param pageNo
	 * @param orgCode
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean ManagementBoardMsg(Integer pageSize, Integer pageNo, String orgCode) {
		PageBean pageBean=new PageBean();
		ManagementBoardModel model=new ManagementBoardModel();
		model.setPageNo(pageNo*pageSize);
		model.setPageSize(pageSize);
		model.setOrgCode(orgCode);
		
		int count=userMsgBoardMapper.selectManagementBoardCount(model);
		List<UserMsgBoard> list=userMsgBoardMapper.selectManagementBoardList(model);
		pageBean.setRecordList(list);
		pageBean.setTotalCount(count);
		return pageBean;
	}
	

	/**
	 * 
	 * @Title: ManagementBoardComment  
	 * @Description: 
	 * @param pageSize
	 * @param pageNo
	 * @param comment
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean getReportBoardList(Integer pageSize, Integer pageNo, String boradCode) {
		PageBean pageBean=new PageBean();
		//查询举报的留言板
		UserMsgBoard reportBoard=userMsgBoardMapper.getReportBoradMsg(boradCode);
		
		GetReportBoradListModel record=new GetReportBoradListModel(); 
		record.setPageNo(pageNo*pageSize);
		record.setPageSize(pageSize);
		record.setUserCode(reportBoard.getUserCode());
		record.setBoradCode(boradCode);
		//查询留言板列表（过滤举报的留言）
		List<UserMsgBoard> list=userMsgBoardMapper.getReportBoradList(record);
		int count=userMsgBoardMapper.getReportBoradCount(record);
		//把举报的放入第一条
		list.add(0,reportBoard);
		MessageBoardReplyModel mbrModel=new MessageBoardReplyModel();
		userMsgBoardReplyMapper.selectCount(mbrModel);
		
		pageBean.setRecordList(list);
		pageBean.setTotalCount(count);
		
		return pageBean;
	}

	
	/**
	 * 
	 * @Title: getReportBoradReply  
	 * @Description: 获取举报留言回复
	 * @param pageSize
	 * @param pageNo
	 * @param replyCode
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean getReportBoradReply(String boradCode,Integer pageSize, Integer id, String replyCode) {
		PageBean pageBean=new PageBean();
		UserMsgBoardReply replyInfo=userMsgBoardReplyMapper.getReportBoradReplyInfo(replyCode);
		GetReportBoradReplyModel  reocrd=new GetReportBoradReplyModel();
		reocrd.setId(id);
		reocrd.setPageSize(pageSize);
		reocrd.setReplyCode(replyCode);
		reocrd.setBoardCode(boradCode);
		
		List<UserMsgBoardReply> list=userMsgBoardReplyMapper.getReportBoradReplyList(reocrd);
		int count=userMsgBoardReplyMapper.getReportBoradReplyCount(reocrd);
		
		list.add(0, replyInfo);
		pageBean.setRecordList(list);
		pageBean.setTotalCount(count);
		
		return pageBean;
	}
	
}