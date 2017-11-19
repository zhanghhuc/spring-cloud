package com.zssq.msg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.AuthConstants;
import com.zssq.constants.CreditConstants;
import com.zssq.constants.StatisticConstants;
import com.zssq.dao.pojo.MessageIntegral;
import com.zssq.dao.pojo.StatisticCommon;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.TeamInfo;
import com.zssq.dao.pojo.UserBoardRemind;
import com.zssq.dao.pojo.UserBordAndReply;
import com.zssq.dao.pojo.UserMsgBoard;
import com.zssq.dao.pojo.UserMsgBoardReply;
import com.zssq.exceptions.BusinessException;
import com.zssq.kafka.core.KafkaProducerTemplate;
import com.zssq.model.MessageBoradModel;
import com.zssq.msg.vo.AddMsgBoardReplyVO;
import com.zssq.msg.vo.AddMsgBoardVO;
import com.zssq.msg.vo.BoardListVO;
import com.zssq.msg.vo.BoardReplyListVO;
import com.zssq.msg.vo.BoradRemindVO;
import com.zssq.msg.vo.DelBoardRemindVO;
import com.zssq.msg.vo.DeleteBoardReplyVO;
import com.zssq.msg.vo.DeleteBoardVO;
import com.zssq.msg.vo.GetBoradReportVO;
import com.zssq.msg.vo.GetReportBoardReplyVO;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IStatisticService;
import com.zssq.service.ISysUserService;
import com.zssq.service.ITeamInfoService;
import com.zssq.service.MessagerBoradReplyService;
import com.zssq.service.MessagerBoradService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;
import com.zssq.utils.PropertiesUtil;

/**
 * 
 * @ClassName: BoardController  
 * @Description: 留言板  
 * @author YDB  
 * @date 2017年3月30日  
 *
 */
@Controller
@RequestMapping("/msg")
public class MsgBoardController {
	private Logger log = LoggerFactory.getLogger(this.getClass());	

	@Autowired
	private MessagerBoradService messagerBoradService;
	
	@Autowired
	private MessagerBoradReplyService messagerBoradReplyService;
	
	@Autowired
	private ISysUserService userService;
	
	@Autowired
	private ITeamInfoService teamService;
	
	@Autowired
	private IStatisticService statisticService;
	
	@Autowired
	private KafkaProducerTemplate producerTeplate;
	
	
	/**
	 * 
	 * @Title: getNewestMsgList  
	 * @Description: 留言板
	 * @param vo 
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getMsgBoardList")
	@ResponseBody
	public ResultJSON getNewestMsgList(@RequireValid BoardListVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		
		try {
			
			MessageBoradModel msgBoradModel=new MessageBoradModel();
			
			msgBoradModel.setPageNo(Integer.parseInt(vo.getPageNo()));
			msgBoradModel.setPageSize(Integer.parseInt(vo.getPageSize()));
		/*	msgBoradModel.setReplyPageNo(vo.getReplyPageNo());
			msgBoradModel.setReplyPageSize(vo.getReplyPageSize());*/
			msgBoradModel.setType(Integer.parseInt(vo.getType()));
			msgBoradModel.setUserCode(vo.getOtherUserCode());
			//查询条件
			PageBean pageBean=messagerBoradService.getMyPublishMessageList(msgBoradModel);
			
			//返回值封装
			List<UserBordAndReply> list=pageBean.getRecordList();
			List<String> userCodeList=new ArrayList<>();
			
			for(int i=0;i<list.size();i++){
				userCodeList.add(list.get(i).getUserCode());
				userCodeList.add(list.get(i).getMessageBoardUserCode());
			}
			
			//获取留言表中的回复userCode
			for(int i=0;i<list.size();i++){
				List<Map<String, Object>> arrlist=list.get(i).getReplyList();		
					for (Map<String, Object> replyInfo : arrlist) {
						userCodeList.add(replyInfo.get("replyUserCode").toString());
						userCodeList.add(replyInfo.get("userCode").toString());
					}
				
			}
			
			//用户信息
			Map<String, Object> userInfoMap=userService.selectMapByCodes(userCodeList);
			
			//添加用户信息
			for(int i=0;i<list.size();i++){
		
				SysUserInfo userInfo=(SysUserInfo) userInfoMap.get(list.get(i).getUserCode());
				if(userInfo!=null){
					list.get(i).setUserName(userInfo.getUserName());
					list.get(i).setUserHead(userInfo.getHeadPortrait());
					list.get(i).setOrgName(userInfo.getManageOrgInfo().getSysOrgName());
				}
				
				SysUserInfo boradUserInfo=(SysUserInfo) userInfoMap.get(list.get(i).getMessageBoardUserCode());
				
				if(boradUserInfo!=null){
					//留言板用户信息封装
					list.get(i).setBoardUserName(boradUserInfo.getUserName());
					list.get(i).setBoardOrgName(boradUserInfo.getManageOrgInfo().getSysOrgName());
					list.get(i).setBoardUserHead(boradUserInfo.getHeadPortrait());
				}
				
				//回复列表添加用户信息
				List<Map<String, Object>> arrlist=list.get(i).getReplyList();
				
				for(Map<String, Object> replyInfo : arrlist) {
					
					SysUserInfo replyUserInfo=(SysUserInfo) userInfoMap.get(replyInfo.get("replyUserCode").toString());
					SysUserInfo user=(SysUserInfo) userInfoMap.get(replyInfo.get("userCode").toString());
					
					if(replyUserInfo!=null){
						//回复列表信息封装
						
						replyInfo.put("replyUserName",user.getUserName());
						replyInfo.put("replyOrgName",user.getManageOrgInfo().getSysOrgName());
						replyInfo.put("replyHead",user.getHeadPortrait());
						replyInfo.put("replyUserCode",user.getUserCode());
						
						replyInfo.put("userCode",replyUserInfo.getUserCode());
						replyInfo.put("userName",replyUserInfo.getUserName());
						replyInfo.put("orgName",replyUserInfo.getManageOrgInfo().getSysOrgName());
						
						
					}
					
				}
				
				
			}

			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("total", pageBean.getTotalCount());
			json.put("msgList", JSONArray.toJSON(list));
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("获取留言表-getMsgBoardList():"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001", "留言");
		}
		
		return resJson;
		
	}
	
	/**
	 * 
	 * @Title: addMessageBoardInfo  
	 * @Description:添加留言
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/addMessageBoardInfo")
	@ResponseBody
	public ResultJSON addMessageBoardInfo(@RequireValid AddMsgBoardVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		String msgCode=UUIDHelper.getUUID();
		JSONObject json=new JSONObject();
		
		SysUserInfo boradUserInfo=userService.selectByCode(vo.getBeMessageUserCode());
		SysUserInfo userInfo=userService.selectByCode(vo.getUserCode());

		try {
			boolean state=messagerBoradService.addMessageBoardInfo(msgCode,vo.getBeMessageUserCode(), vo.getUserCode(), vo.getContent(), boradUserInfo.getManageOrgInfo().getSysOrgCode(), AuthConstants.TENANT_CODE);
			//成功
			m = PropertiesUtil.getMessage("COMMON_200");
			json.put("result",state?0:1 );
			json.put("orgName", boradUserInfo.getManageOrgInfo().getSysOrgName());
			json.put("userHead", boradUserInfo.getHeadPortrait());
			json.put("userName", boradUserInfo.getUserName());
			json.put("content", vo.getContent());
			json.put("createTime",DateUtils.getTime());
			
			json.put("messageBoardUserCode", vo.getUserCode());
			json.put("messageBoardCode", msgCode);
			json.put("boardUserName",userInfo.getUserName());
			json.put("boardOrgName", userInfo.getManageOrgInfo().getSysOrgName());
			json.put("boardUserHead", userInfo.getHeadPortrait());
			json.put("replyCount",0);
			json.put("replyList",new JSONArray());
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
			
			
			StatisticCommon statisticCommon = new StatisticCommon();
			statisticCommon.setOrgCode(boradUserInfo.getManageOrgInfo().getSysOrgCode());
			statisticCommon.setPeopleCode(boradUserInfo.getUserCode());
			statisticCommon.setPeopleName(boradUserInfo.getUserName());
			statisticCommon.setMessage(StatisticConstants.MESSAGE);
			statisticService.addRecord(statisticCommon);
			
			
			MessageIntegral data=new MessageIntegral();
			data.setAccountCode(vo.getUserCode());
			data.setAccountType((byte)1);
			data.setActionCode(CreditConstants.COMMAND_MESSAGE_PUBLISH);
			data.setManageOrgCode(userInfo.getManageOrgInfo().getManOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT,JSONObject.toJSONString(data));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("添加留言-addMessageBoardInfo():"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001", "留言");
		}
		
		return resJson;
		
	}
	
	
	/**
	 * 
	 * @Title: delMessageBoard  
	 * @Description:删除留言
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/delMessageBoardInfo")
	@ResponseBody
	public ResultJSON delMessageBoard(@RequireValid DeleteBoardVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();

		try {
			
			boolean state=false;
			if(vo.getType()==1){
				
				state=messagerBoradService.delMsgBoard(vo.getMessageBoardCode());
				
			}else{
				state=messagerBoradService.delMyMsgBoard(vo.getMessageBoardCode());
				
			}
			
			
			SysUserInfo userInfo=userService.selectByCode(vo.getUserCode());
			MessageIntegral data=new MessageIntegral();
			data.setAccountCode(vo.getUserCode());
			data.setAccountType((byte)1);
			data.setActionCode(CreditConstants.COMMAND_MESSAGE_DEL);
			data.setManageOrgCode(userInfo.getManageOrgInfo().getManOrgCode());
			producerTeplate.send(CreditConstants.CREDIT_INCREMENT,JSONObject.toJSONString(data));
			
			
			m = PropertiesUtil.getMessage("COMMON_200");

			if(state){
				//成功
				json.put("result",state?0:1);
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
			}else{
				throw new BusinessException();
			}
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("删除留言板-delMessageBoardInfo():"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001","删除");
		}
		
		return resJson;
		
	}
	
	/**
	 * 
	 * @Title: getBoardReplyList  
	 * @Description: 留言板回复列表查询
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getBoardReplyList")
	@ResponseBody
	public ResultJSON getBoardReplyList(@RequireValid BoardReplyListVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		try {
			//查询条件
			PageBean pageBean=messagerBoradReplyService.getBoardReplyList(vo.getId()==null?"":vo.getId(),vo.getBoardCode(),Integer.parseInt(vo.getPageSize()));
			List<UserMsgBoardReply> list=pageBean.getRecordList();
			
			List<String> userCodeList=new ArrayList<>();
			
			
			for(int i=0;i<list.size();i++){
				userCodeList.add(list.get(i).getUserCode());
				userCodeList.add(list.get(i).getReplyUserCode());
			}
			Map<String, Object> userInfoMap=userService.selectMapByCodes(userCodeList);
			
			//返回值封装
			for(int i=0;i<list.size();i++){
				
				json.put("replyCode", list.get(i).getReplyCode());
				json.put("replyContent", list.get(i).getReplyContent());
				json.put("replyCreatTime",list.get(i).getCreatTime());
			
		/*		SysUserInfo replyUserInfo=(SysUserInfo) userInfoMap.get(list.get(i).getReplyUserCode());
				
				SysUserInfo userInfo=(SysUserInfo) userInfoMap.get(list.get(i).getUserCode());*/

		/*		json.put("userHead", userInfo.getHeadPortrait());
				json.put("orgName",  userInfo.getManageOrgInfo().getSysOrgName());
				json.put("userCode", list.get(i).getUserCode());
				json.put("userName", userInfo.getUserName());
				
				json.put("replyOrgName",replyUserInfo.getManageOrgInfo().getSysOrgName() );
				json.put("replyUserName", replyUserInfo.getUserName());*/
					
				SysUserInfo replyUserInfo=userService.selectByCode(list.get(i).getUserCode());
				SysUserInfo userInfo=userService.selectByCode(list.get(i).getReplyUserCode());

				json.put("orgName",  userInfo.getManageOrgInfo().getSysOrgName());
				json.put("userCode", userInfo.getUserCode());
				json.put("userName", userInfo.getUserName());
				
				json.put("replyUserName", replyUserInfo.getUserName());
				json.put("replyHead", replyUserInfo.getHeadPortrait());
				json.put("replyOrgName",  replyUserInfo.getManageOrgInfo().getSysOrgName());
				json.put("replyUserCode",replyUserInfo.getUserCode());
				json.put("id",list.get(i).getId());
				
				
				
				jsonArray.add(json);
				json=new JSONObject();
			}
			
			json.put("total", pageBean.getTotalCount());
			json.put("replyList", jsonArray);
			
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("留言板回复列表查询-getBoardReplyList:"+JSONObject.toJSONString(vo), e);
			throw new BusinessException().build("MSG_26001","获取列表");
		}
		
		return resJson;
		
	}

	
	 
	/**
	 * 
	 * @Title: delMessageBoard  
	 * @Description:删除留言板回复
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/delMessageReply")
	@ResponseBody
	public ResultJSON delMessageReply(@RequireValid DeleteBoardReplyVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		String msgCode=UUIDHelper.getUUID();
		JSONObject json=new JSONObject();

		try {
			
			boolean state=false;
			state=messagerBoradReplyService.delBoardReply(vo.getMessageReplyCode());
			if(state){
				//成功
				m = PropertiesUtil.getMessage("COMMON_200");

				json.put("boardCode", msgCode);
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
			}else{
				throw new BusinessException();
			}
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("删除留言板回复-delMessageReply():"+JSONObject.toJSON(vo),e);
			throw new BusinessException().build("MSG_26001","删除");
		}
		
		return resJson;
	}
	
	

	/**
	 * 
	 * @Title: addMessageBoardInfo  
	 * @Description:添加留言回复
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/addMessageReply")
	@ResponseBody
	public ResultJSON addMessageReply(@RequireValid AddMsgBoardReplyVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		String msgCode=UUIDHelper.getUUID();
		

		try {
			UserMsgBoardReply reply=new UserMsgBoardReply();
			
			SysUserInfo userinfo=userService.selectByCode(vo.getUserCode());
			
			reply.setReplyCode(msgCode);
			reply.setCreatTime(DateUtils.getTime());
			reply.setIsDelete((byte)0);
			reply.setReplyContent(vo.getContent());
			reply.setUserCode(vo.getUserCode());
			reply.setBoard(vo.getMessageBoardCode());
			reply.setTenantCode(AuthConstants.TENANT_CODE);
			reply.setOrgCode(userinfo.getManageOrgInfo().getSysOrgCode());
			reply.setTenantCode(AuthConstants.TENANT_CODE);
			reply.setReplyUserCode(vo.getBeReplyUserCode());
			
			boolean state=messagerBoradService.addBoardReply(reply,vo.getOrgContent(),vo.getUserCode());
		
			
			JSONObject retJson=JSONObject.parseObject(JSONObject.toJSONString(reply));
			//成功
			retJson.put("result",state?0:1 );
			
			retJson.put("replyCode",reply.getReplyCode());
			retJson.put("replyContent",reply.getReplyContent());
			retJson.put("replyCreatTime",reply.getCreatTime());
			
			
			
			SysUserInfo replyUserInfo=userService.selectByCode(vo.getUserCode());
			
			SysUserInfo userInfo=userService.selectByCode(vo.getBeReplyUserCode());

			retJson.put("orgName",  userInfo.getManageOrgInfo().getSysOrgName());
			retJson.put("userCode", userInfo.getUserCode());
			retJson.put("userName", userInfo.getUserName());
			
			retJson.put("replyUserName", replyUserInfo.getUserName());
			retJson.put("replyHead", replyUserInfo.getHeadPortrait());
			retJson.put("replyOrgName",  replyUserInfo.getManageOrgInfo().getSysOrgName());
			retJson.put("replyUserCode",replyUserInfo.getUserCode());
				
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(retJson));
		
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("添加留言板回复信息-addMessageReply():"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001", "留言");
		}
		
		return resJson;
		
	}
	
	
	
	/**
	 * 
	 * @Title: getBoardRemind  
	 * @Description: 留言板提醒
	 * @param vo 
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getBoardRemind")
	@ResponseBody
	public ResultJSON getBoardRemind(@RequireValid BoradRemindVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();
		JSONArray arrayList=new JSONArray();
		try {
			
			//查询条件
			PageBean pageBean=messagerBoradService.getBoardRemind(vo.getUserCode(),Integer.parseInt(vo.getPageNo()),Integer.parseInt(vo.getPageSize()));
			
			//返回值封装
			List<UserBoardRemind> list=pageBean.getRecordList();
			
			List<String> userCodeList=new ArrayList<>();
			
			for(int i=0;i<list.size();i++){
				String userCode=list.get(i).getUserCode();
				String  sendUserCode=list.get(i).getSendUserCode();
				String receiveUsercode=list.get(i).getReceiveUserCode();
				if(userCode!=null ){
					userCodeList.add(userCode);
				}
				if(sendUserCode!=null){
					userCodeList.add(sendUserCode);				
				}
				if(receiveUsercode!=null ){
					userCodeList.add(receiveUsercode);
				}
			}
			
			//用户信息
			Map<String, Object> userInfoMap=userService.selectMapByCodes(userCodeList);
			
			//添加用户信息
			for(int i=0;i<list.size();i++){
				
				//班组留言加上班组名称
				if(list.get(i).getMsgType()==2){
					TeamInfo teamInfo=teamService.selectByCode(list.get(i).getTeamCode());
					json.put("bzName",teamInfo.getTeamName());
				}
				SysUserInfo sendUserInfo=(SysUserInfo)userInfoMap.get(list.get(i).getSendUserCode());
				SysUserInfo userInfo=(SysUserInfo)userInfoMap.get(list.get(i).getReceiveUserCode());
				
				
				json.put("userName",userInfo.getUserName());
				json.put("userHead", userInfo.getHeadPortrait());
				json.put("orgName", userInfo.getManageOrgInfo().getSysOrgName());
				json.put("userCode", userInfo.getUserCode());
				

				json.put("sendUserName", sendUserInfo.getUserName());
				json.put("sendUserCode",sendUserInfo.getUserCode());
				json.put("sendUserHead", sendUserInfo.getHeadPortrait());
				json.put("sendOrgName",sendUserInfo.getManageOrgInfo().getSysOrgName());

				json.put("originalContent",list.get(i).getOriginalContent());
				json.put("content",list.get(i).getMsgContent());
				//班组还是个人
				json.put("type",list.get(i).getMsgType());
				json.put("boradCode", list.get(i).getBoradCode());
				
				if(list.get(i).getSendUserCode().equals(vo.getUserCode())){
					//自己发出是信息
					json.put("msgType", "1");
				}else{
					//别人发出的
					json.put("msgType", "2");
				}
				
				json.put("createTime",list.get(i).getCreateTime());
				json.put("msgCode", list.get(i).getMsgCode());
				arrayList.add(json);
				json=new JSONObject();
			}

			m = PropertiesUtil.getMessage("COMMON_200");
			
			json.put("totalCount", pageBean.getTotalCount());
			json.put("msgList", arrayList);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	System.out.println(JSONObject.toJSON(json));
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("留言板提醒-getBoardRemind():"+JSONObject.toJSONString(vo),e);
			
			throw new BusinessException().build("MSG_26001", "留言");
		}
		
		return resJson;
		
	}
	
	
	
	/**
	 * 
	 * @Title: delBoardRemind 
	 * @Description:删除留言
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/delBoardRemind")
	@ResponseBody
	public ResultJSON delBoardRemind(@RequireValid DelBoardRemindVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();

		try {
			
			boolean state=false;
			state=messagerBoradService.delBoardRemind(vo.getMsgCode());
			
			m = PropertiesUtil.getMessage("COMMON_200");
		
			//成功
			json.put("result",state?0:1);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
    	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("删除留言板消息提醒-delBoardRemind():"+JSONObject.toJSONString(vo));
			
			throw new BusinessException().build("MSG_26001","删除");
		}
		
		return resJson;
		
	}
	
	
	
	
	
	
	/**
	 * 
	 * @Title: getReportBoardMsgList  
	 * @Description: 获取举报列表
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	
	@RequestMapping("/getReportBoardMsgList")
	@ResponseBody
	public ResultJSON getReportBoardMsgList(@RequireValid GetBoradReportVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		JSONArray jsonList=new JSONArray();
		
		try {
			PageBean pageBean=messagerBoradService.getReportBoardList(Integer.parseInt(vo.getPageSize()),Integer.parseInt( vo.getPageNo()),vo.getBoradCode());
			List<UserMsgBoard> boardList= pageBean.getRecordList();		
			List<String> userCodeList=new ArrayList<>();
			for(int i=0;i<boardList.size();i++){
				userCodeList.add(boardList.get(i).getUserCode());
				userCodeList.add(boardList.get(i).getMessageBoardUserCode());
			}
			//用户信息
			Map<String, Object> userInfoMap=userService.selectMapByCodes(userCodeList);
			//封装返回参数
			for (UserMsgBoard boradInfo : boardList) {
			
				SysUserInfo userInfo=(SysUserInfo) userInfoMap.get(boradInfo.getUserCode());
				SysUserInfo sendUserInfo=(SysUserInfo) userInfoMap.get(boradInfo.getMessageBoardUserCode());
				json.put("messageBoardCode", boradInfo.getMessageBoardCode());
				json.put("userCode", boradInfo.getUserCode());
				json.put("messageBoardUserCode",boradInfo.getMessageBoardUserCode());
				json.put("createTime", boradInfo.getCreateTime());
				json.put("orgCode",boradInfo.getOrgCode());
				json.put("content", boradInfo.getContent());
				json.put("orgName",userInfo.getManageOrgInfo().getSysOrgName());
				json.put("userHead", userInfo.getHeadPortrait());
				json.put("boardUserHead", sendUserInfo.getHeadPortrait());
				json.put("boardOrgName", sendUserInfo.getManageOrgInfo().getSysOrgName());
				json.put("boardUserName", sendUserInfo.getUserName());
				json.put("userName", userInfo.getUserName());
				jsonList.add(json);
				json=new JSONObject();
			}
			
			m = PropertiesUtil.getMessage("COMMON_200");
			json.put("totalCount", pageBean.getTotalCount());
			json.put("msgList", jsonList);
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
			
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("获取举报留言:"+JSONObject.toJSONString(vo));
			
			throw new BusinessException().build("MSG_26001","留言");
		}
		
		return resJson;
		
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: getReportBoardReplyMsgList  
	 * @Description: 获取举报回复列表
	 * @return    参数  
	 * @return: ResultJSON    返回类型
	 * @throws BusinessException 
	 */
	@RequestMapping("/getReportBoardReplyMsgList")
	@ResponseBody
	public ResultJSON getReportBoardReplyMsgList(@RequireValid GetReportBoardReplyVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		JSONArray jsonList=new JSONArray();	
		
		PageBean pageBean=messagerBoradService.getReportBoradReply(vo.getBoardCode(),Integer.parseInt(vo.getPageSize()),vo.getId(),vo.getReplyCode());
		List<UserMsgBoardReply> replyList= pageBean.getRecordList();		
		
		for(int i=0;i<replyList.size();i++){
			
			json.put("replyCode", replyList.get(i).getReplyCode());
			json.put("replyContent", replyList.get(i).getReplyContent());
			json.put("replyCreatTime",replyList.get(i).getCreatTime());
				
			SysUserInfo replyUserInfo=userService.selectByCode(replyList.get(i).getUserCode());
			SysUserInfo userInfo=userService.selectByCode(replyList.get(i).getReplyUserCode());

			json.put("orgName",  userInfo.getManageOrgInfo().getSysOrgName());
			json.put("userCode", userInfo.getUserCode());
			json.put("userName", userInfo.getUserName());
			
			json.put("replyUserName", replyUserInfo.getUserName());
			json.put("replyHead", replyUserInfo.getHeadPortrait());
			json.put("replyOrgName",  replyUserInfo.getManageOrgInfo().getSysOrgName());
			json.put("replyUserCode",replyUserInfo.getUserCode());
			
			jsonList.add(json);
			json=new JSONObject();
		}
			
		json.put("total", pageBean.getTotalCount());
		json.put("replyList", jsonList);
		
		m = PropertiesUtil.getMessage("COMMON_200");
		resJson.setReturnCode(m.getCode());
		resJson.setReturnTip(m.getTip());
		resJson.setBody(JSONObject.toJSON(json));
		
		return resJson;
		
	}
	
	
	
	
	
	
	
	
}