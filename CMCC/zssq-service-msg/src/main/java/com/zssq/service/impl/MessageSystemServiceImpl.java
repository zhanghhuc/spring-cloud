package com.zssq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zssq.dao.mapper.UserMsgSysMapper;
import com.zssq.dao.mapper.UserMsgSysUsMapper;
import com.zssq.dao.pojo.UserMsgSys;
import com.zssq.dao.pojo.UserMsgSysUs;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageAddSystemModel;
import com.zssq.model.MessageSysUserMsgModel;
import com.zssq.model.MessageSystemModel;
import com.zssq.model.TempDelMsgSysModel;
import com.zssq.model.TempMsgSystem;
import com.zssq.service.MessageSystemService;
import com.zssq.shiro.mysecurity.UUIDHelper;
import com.zssq.util.RedisUtil;
import com.zssq.utils.DateUtils;
import com.zssq.utils.PageBean;

/**
 * 
 * @ClassName: MessageSystemServiceImpl  
 * @Description: 系统消息  
 * @author YDB  
 * @date 2017年4月5日  
 *
 */
@Service("messageSystemService")
public class MessageSystemServiceImpl implements MessageSystemService{

	
	@Autowired
	private UserMsgSysMapper  userMsgSysMapper;
		
	@Autowired
	private UserMsgSysUsMapper userMsgSysUsMapperl;
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 
	 * @Title: addMessageSystem  
	 * @Description: 添加消息
	 * @param sysModel
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean addMessageSystem(MessageAddSystemModel sysModel) throws BusinessException{
		
		UserMsgSys record=new UserMsgSys();
		Long date=DateUtils.getTime();
		record.setSysCode(UUIDHelper.getUUID());
		record.setCreateTime(date);
		record.setIsDelete((byte)0);
		record.setOrgCode(sysModel.getOrgCode());
		record.setShowOrgCode(sysModel.getShowOrgCode());
		record.setTenantCode(sysModel.getTenantCode());
		record.setContent(sysModel.getContent());
		record.setUserCode(sysModel.getUserCode());
		record.setTitle(sysModel.getTitle());
		//record.setShowType(sysModel.getShowType());
		
		//根据不同人的级别设定发布范围--- 1.集团 2省级 3市级,4省级以下,5集团以下
		record.setShowType(3);
		
		int state=userMsgSysMapper.insert(record);
		
		return state==1?true:false;
	}


	
	/**
	 * 
	 * @Title: getSystemMsgList  
	 * @Description: 获取消息列表
	 * @param sysModel
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean getSystemMsgList(MessageSystemModel sysModel) throws BusinessException {
		redisUtil.delMsgNumber(sysModel.getUserCode(), 7);
		
		PageBean pageBean=new PageBean();
		sysModel.setPageNo(sysModel.getPageNo()*sysModel.getPageSize());
		int total=userMsgSysMapper.selectCount(sysModel);
		List<UserMsgSys> list=userMsgSysMapper.selectPage(sysModel);
		pageBean.setTotalCount(total);
		pageBean.setRecordList(list);
		return pageBean;
	}

	
	/**
	 * 
	 * @Title: delMessageSystem  
	 * @Description: 删除系统消息
	 * @param msgCode
	 * @param userCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean delMessageSystem(String msgCode, String userCode)throws BusinessException {
		UserMsgSys record=new UserMsgSys();
		record.setUserCode(userCode);
		record.setSysCode(msgCode);
		record.setIsDelete((byte)1);
		int state=userMsgSysMapper.delMessageSystem(record);
		return state==1?true:false;
	}
	

	/**
	 * 
	 * @Title: getPcSystemMsg  
	 * @Description: PC用户端获取系统消息
	 * @return    参数  
	 * @return: PageBean    返回类型
	 */
	@Override
	public PageBean updateAndGetPcSystemMsgList(MessageSysUserMsgModel sysMsg) throws BusinessException {
		sysMsg.setPageNo(sysMsg.getPageNo()*sysMsg.getPageSize());
		PageBean pageBean=new PageBean();
		List<UserMsgSys> listRet=new ArrayList<>();
		//第一页调用更新
		if(sysMsg.getPageNo()==0){
			updateUserSystemMsg(sysMsg);
		}
		List<String> msgCodeList=new ArrayList<>();
		
		//查询消息信息
		int total=userMsgSysUsMapperl.selectCount(sysMsg);
		pageBean.setTotalCount(total);
		List<UserMsgSysUs> list=userMsgSysUsMapperl.selectPage(sysMsg);
			
		if(list.size()==0){
			return pageBean;
		}
		
		
		for(UserMsgSysUs us:list){
			msgCodeList.add(us.getSysCode());
		}
		//根据消息中的msgCode 查询消息code
		List<UserMsgSys> listInfo=userMsgSysMapper.selectUserMsgInfo(msgCodeList);
		
/*		JSONObject json=new JSONObject();
		UserMsgSys sys=null;
		UserMsgSysUs us=null;
		
		//封装返回json
		for (int i=0;i<list.size();i++) {
			us=list.get(i);
			for(int j=0;j<listInfo.size();j++){
				sys=listInfo.get(j);
				if(us.getSysCode().equals(sys.getSysCode())){
					json=new JSONObject();
					json.put("title",sys.getTitle() );
					json.put("infoMsgCode", sys.getSysCode());
					json.put("createTime", sys.getCreateTime());
					json.put("userMsgCode", us.getSysCode());
					json.put("infoCode", us.getId());
					
					
					
					listRet.add(sys);
				}
			}
		}*/
		pageBean.setRecordList(listInfo);
		return pageBean;
	}
	
	
	
	
	/**
	 * 
	 * @Title: updateUserSystemMsg  
	 * @Description:   更新用户系统消息 关系表
	 * @return: void    返回类型
	 */
	private void updateUserSystemMsg(MessageSysUserMsgModel record )throws BusinessException{
		List<TempMsgSystem> newMsgCodeList=new ArrayList<>();
		
		//获取用户级-1.集团 2省级 3市级,4省级以下,5集团以下
		String showType="3";
		
		//查询出符合当前用户机构CODE系统消息 -时间排序
		List<String> list=new ArrayList<>();
		list.add(record.getOrgCode());
		
	/*	if(!StringUtils.isEmpty(record.getOrgCode())){
			list.add(record.getOrgCode());
		}*/
	/*	if(!StringUtils.isEmpty(record.getOrgCode2())){
			list.add(record.getOrgCode2());
		}
		if(!StringUtils.isEmpty(record.getOrgCode3())){
			list.add(record.getOrgCode3());
		}*/
		
		//获取到最新的消息list-根据级别类型获取---->这一版获取当前级别
		List<UserMsgSys> msgList=userMsgSysMapper.selectUserMsg(list);
		
		//获取到用户最新的消息
		List<UserMsgSysUs> uslist=userMsgSysUsMapperl.selectUseNewsMsg(record.getUserCode());
		
		//当用户没有消息时候会插入一条最新的空消息，用来做更新判断使用
		if(uslist.size()==0 && msgList.size() !=0){
			UserMsgSys msg=msgList.get(0);
			UserMsgSysUs ums=new UserMsgSysUs();
			ums.setCreateTime(msg.getCreateTime());
			ums.setUserCode(record.getUserCode());
			ums.setSysCode(msg.getSysCode());
			userMsgSysUsMapperl.insert(ums);
			return ;
		}
		
		//如果消息是空的 并且用户关系表是空的
		if(uslist.size()==0 && msgList.size() ==0){
			UserMsgSysUs ums=new UserMsgSysUs();
			ums.setCreateTime(DateUtils.getTime());
			ums.setUserCode(record.getUserCode());
			userMsgSysUsMapperl.insert(ums);
			return ;
		}
		
		
		//根据用户最后的创建时间，取出大于当前用户最新的消息
		TempMsgSystem temp;
		for (UserMsgSys userMsgSysUs : msgList) {
			if(userMsgSysUs.getCreateTime()> uslist.get(0).getCreateTime()){
				temp=new TempMsgSystem();
				temp.setCode(userMsgSysUs.getSysCode());
				temp.setCreatTime(userMsgSysUs.getCreateTime());
				newMsgCodeList.add(temp);
			}
		}
		
		//取到最新消息 
		List<UserMsgSysUs> newSaveList=new ArrayList<>();
		UserMsgSysUs userMsgSysUs;
		for (TempMsgSystem tempMsg : newMsgCodeList) {
			userMsgSysUs=new UserMsgSysUs();
			userMsgSysUs.setCreateTime(tempMsg.getCreatTime());
			userMsgSysUs.setSysCode(tempMsg.getCode());
			userMsgSysUs.setUserCode(record.getUserCode());
			newSaveList.add(userMsgSysUs);
		}
		
		//是否批量插入  删除消息code 为空的	
		if(newSaveList.size()>=2){
			userMsgSysUsMapperl.batchMsg(newSaveList);
			TempDelMsgSysModel model =new TempDelMsgSysModel();
			model.setUserCode(record.getUserCode());
			userMsgSysUsMapperl.delGarbage(model);
		}else if(newSaveList.size()==1){
			TempDelMsgSysModel model =new TempDelMsgSysModel();
			model.setUserCode(record.getUserCode());
			userMsgSysUsMapperl.delGarbage(model);
			userMsgSysUsMapperl.insert(newSaveList.get(0));
		}
		
	}

	/**
	 * 
	 * @Title: delUserMsg  
	 * @Description: 删除用户系统消息
	 * @param userCode
	 * @param msgCode
	 * @return    参数  
	 * @return: boolean    返回类型
	 */
	@Override
	public boolean deleteUserMsg(String userCode, String msgCode)  throws BusinessException{
		int state=0;
		
		TempDelMsgSysModel model=new TempDelMsgSysModel();
		
		model.setUserCode(userCode);
		model.setMsgCode(msgCode);
		//查询还有多少条
		int total=userMsgSysUsMapperl.selCountDelete(model);
		
		//如果还有一条则更新code
		if(total==1){
			state=userMsgSysUsMapperl.delLastUserMsg(model);
			return state==0?false:true;
		}else{
			//如果有多条，删除后更新最后一条的创建时间
			
			//需要删除消息的时间
			UserMsgSysUs thisMsg =userMsgSysUsMapperl.getUserMsgTime(model);
			
			
			TempDelMsgSysModel newModel=new TempDelMsgSysModel();
			newModel.setUserCode(userCode);
			//获取最后一条信息的时间
			UserMsgSysUs msgLast=userMsgSysUsMapperl.getUserMsgTime(newModel);
		
			
			//删除的最新一条,更新下一条时间
			if(msgLast.getId().equals(thisMsg.getId())){
				
				//拿出时间，删除后，更新下一条的时间
				Long creatTime=thisMsg.getCreateTime();
				userMsgSysUsMapperl.delUserMsg(model);
				model.setMsgCode(null);
				UserMsgSysUs newMsg=userMsgSysUsMapperl.getUserMsgTime(newModel);
				model.setMsgCode(newMsg.getSysCode());
				model.setUserCode(newMsg.getUserCode());
				model.setCreateTime(creatTime);
				state= userMsgSysUsMapperl.updateUserMsgTime(model);
				return state==0?false:true;
				
			}else{
				
				//不是最新一条,直接删除
				state=userMsgSysUsMapperl.delUserMsg(model);
				return state==0?false:true;
			} 
			
		}
		
	}



	/**
	 * 
	 * @Title: getMsgSysInfo  
	 * @Description: 获取系统消息详细信息
	 * @param msgCode
	 * @return    参数  
	 * @return: UserMsgSys    返回类型
	 */
	@Override
	public UserMsgSys getMsgSysInfo(String msgCode) throws BusinessException {
		
		return userMsgSysMapper.getMsgSysInfo(msgCode);
	}
	
	
	
	
}