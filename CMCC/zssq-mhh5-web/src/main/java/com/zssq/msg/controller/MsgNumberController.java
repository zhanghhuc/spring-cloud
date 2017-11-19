package com.zssq.msg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.msg.vo.DelMsgNumberVO;
import com.zssq.msg.vo.MsgNumberVO;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysUserService;
import com.zssq.service.MessageNumberService;
import com.zssq.utils.PropertiesUtil;

/**
 * 
 * @ClassName: MsgNumberController  
 * @Description: 消息数量  
 * @author YDB  
 * @date 2017年4月11日  
 *
 */
@Controller
@RequestMapping("/msg")
public class MsgNumberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());	


	@Autowired
	private MessageNumberService messageNumberService;
	
	@Autowired
	private ISysUserService userService;
	
	/**
	 * 
	 * @Title: getMsgNumber  
	 * @Description: 获取数量
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getMsgNumber")
	@ResponseBody
	public ResultJSON getMsgNumber(@RequireValid  MsgNumberVO vo) throws BusinessException{

		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		try {
			
			SysUserInfo userInfo=userService.selectByCode(vo.getUserCode());
			if(userInfo==null){
				throw new BusinessException();
			}
			
			//查询条件
			json=messageNumberService.getMsgNumber(vo.getUserCode());
			
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("消息数量获取-getMsgNumber："+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26002","");
		}
		
		return resJson;
	
	}
	
	

	/**
	 * 
	 * @Title: delMsgNumber  
	 * @Description: 删除消息数量
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/delMsgNumber")
	@ResponseBody
	public ResultJSON delMsgNumber(@RequireValid DelMsgNumberVO vo) throws BusinessException{

		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		try {
			
			messageNumberService.MsgDelNumber(vo.getUserCode(), vo.getType());
			
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {
			log.error("删除消息数量-delMsgNumber:"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26002","");
		}
		
		return resJson;
	
	}
	
}
