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
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.dao.pojo.UserMsgSys;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageSysUserMsgModel;
import com.zssq.msg.vo.DeleteSystemVO;
import com.zssq.msg.vo.SystemInfoVO;
import com.zssq.msg.vo.SystemPcListVO;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.service.MessageSystemService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PropertiesUtil;


/**
 * 
 * @ClassName: MsgSystemController  
 * @Description: 系統消息  
 * @author YDB  
 * @date 2017年4月5日  
 *
 */
@Controller
@RequestMapping("/msg")
public class MsgSystemController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());	


	@Autowired
	private MessageSystemService messageSystemService;
	
	@Autowired
	private ISysUserService userService;
	
	
	
	
	
	
	/**
	 * 
	 * @Title: delMessageSystem  
	 * @Description:删除系统消息
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/deleteMsgInfo")
	@ResponseBody
	public ResultJSON delMessageSystem(@RequireValid DeleteSystemVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		JSONObject json=new JSONObject();

		try {
			
			boolean state=false;
			state=messageSystemService.deleteUserMsg(vo.getUserCode(),vo.getMsgCode());
					
			if(state){
				//成功
				json.put("result",state?0:1);
				m = PropertiesUtil.getMessage("COMMON_200");
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
			}else{
				//失败
			}
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("删除系统消息-deleteMsgInfo:"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001","删除");
		}
		
		return resJson;
		
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: getSystemMsgList  
	 * @Description: 获取系统消息列表
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getSystemMsgList")
	@ResponseBody
	public ResultJSON getSystemMsgList(@RequireValid SystemPcListVO vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		try {
			
			MessageSysUserMsgModel sysMsg=new MessageSysUserMsgModel();
			
			SysUserInfo userInfo=userService.selectByCode(vo.getUserCode());
			
			sysMsg.setOrgCode(userInfo.getOrgCode());
			sysMsg.setPageNo(Integer.parseInt(vo.getPageNo()));
			sysMsg.setPageSize(Integer.parseInt(vo.getPageSize()));
			sysMsg.setUserCode(vo.getUserCode());
			
			//查询条件
			PageBean pageBean=messageSystemService.updateAndGetPcSystemMsgList(sysMsg);
			List<UserMsgSys> list=pageBean.getRecordList();
			
			if(list==null || list.size()==0){
				
				json.put("total", 0);
				json.put("msgList", new JSONArray());
				
				m = PropertiesUtil.getMessage("COMMON_200");
				resJson.setReturnCode(m.getCode());
				resJson.setReturnTip(m.getTip());
				resJson.setBody(JSONObject.toJSON(json));
				
				return resJson;
			}
			
			List<String> userCodeList=new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				userCodeList.add(list.get(i).getUserCode());
			}
			
			//List<SysUserInfo> userInfoList=userService.selectByCodes(userCodeList);
			
			Map<String, Object> userMap=userService.selectMapByCodes(userCodeList);
			
			
			for(int i=0;i<list.size();i++){
				UserMsgSys temp=list.get(i);
				json.put("infoCode",temp.getSysCode());
				json.put("infoTitle",temp.getTitle());
				json.put("infoPublisherTime",temp.getCreateTime());
				json.put("userMsgCode",temp.getUserCode());
			
				/*for (SysUserInfo userInfos:userInfoList) {
					if(list.get(i).getUserCode().equals(userInfos.getUserCode())){
						json.put("headPortrait",userInfos.getHeadPortrait());
						continue;
					}
				}*/
				SysUserInfo userInfos=(SysUserInfo) userMap.get(list.get(i).getUserCode());
				if(userInfos!=null){
					json.put("headPortrait",userInfos.getHeadPortrait());
				}else{
					json.put("headPortrait","");
				}
				json.put("orgName", userInfos.getManageOrgInfo().getSysOrgName());
				jsonArray.add(json);
				json=new JSONObject();
			}
			
			json.put("total", pageBean.getTotalCount());
			json.put("msgList", jsonArray);
			
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("获取系统消息-getSystemMsgList:"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001","获取列表");
		}
		
		return resJson;
		
	}
	
	
	
	/**
	 * 
	 * @Title: getSystemMsgInfo  
	 * @Description: 获取系统消息详情
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getSystemMsgInfo")
	@ResponseBody
	public ResultJSON getSystemMsgInfo(@RequireValid SystemInfoVO vo) throws BusinessException{

		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		try {
			
			//查询条件
			UserMsgSys info=messageSystemService.getMsgSysInfo(vo.getMsgCode());
			
			// 根据用户userCode获取用户个人信息
			SysUserInfo userInfo = userService.selectByCode(info.getUserCode());
			
			json.put("msgInfo",info.getContent());
			json.put("title", info.getTitle());
			json.put("createTime", info.getCreateTime());
			json.put("userName",userInfo.getUserName());
			json.put("imgUrl", userInfo.getHeadPortrait());
			json.put("orgName",userInfo.getManageOrgInfo().getSysOrgName());
			
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("获取系统消息详情-getSystemMsgInfo:"+JSONObject.toJSONString(json),e);
			throw new BusinessException().build("MSG_26001","获取消息详情");
		}
		
		return resJson;
	
	}
	
}