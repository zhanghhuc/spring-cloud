package com.zssq.msg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.dao.pojo.UserMsgNotice;
import com.zssq.exceptions.BusinessException;
import com.zssq.model.MessageNoticeListModel;
import com.zssq.msg.vo.MsgSysNoticeInfoVO;
import com.zssq.msg.vo.MsgSysNoticeListVO;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.MessageSystemNoticeService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PropertiesUtil;

/**
 * 
 * @ClassName: MsgSystemNoticeController  
 * @Description: 系统通知
 * @author YDB  
 * @date 2017年3月31日  
 *
 */
@Controller
@RequestMapping("/msg")
public class MsgSystemNoticeController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());	

	
	@Autowired
	private MessageSystemNoticeService noticeService;
	
	
	/**
	 * 
	 * @Title: getMsgSubscribesList  
	 * @Description: 获取订阅通知列表
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getMsgNoticeList")
	@ResponseBody
	public ResultJSON getMsgNoticeList(@RequireValid MsgSysNoticeListVO  vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		try {
			MessageNoticeListModel mdel=new MessageNoticeListModel();
			mdel.setPageNo(Integer.parseInt(vo.getPageNo()));
			mdel.setPageSize(Integer.parseInt(vo.getPageSize()));
			mdel.setUserCode(vo.getUsersCode());
			
			//查询条件
			PageBean pageBean=noticeService.getNoticeList(mdel);
			List<UserMsgNotice> list=pageBean.getRecordList();
			
			for(int i=0;i<list.size();i++){
				UserMsgNotice notice=list.get(i);
				json.put("title",notice.getTitle());
				json.put("createTime", notice.getCreateTime());
				json.put("msgCode",notice.getNoticeCode());
				jsonArray.add(json);
				json=new JSONObject();
			}
			
			json.put("total", pageBean.getTotalCount());
			json.put("list", jsonArray);
			
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
        	
		} catch (BusinessException e) {
			throw e;
			
		} catch (Exception e) {		
			log.error("获取系统通知-getMsgNoticeList:"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001", "获取列表");

		}
		
		return resJson;
		
	}
	
	
	
	
	/**
	 * 
	 * @Title: getMsgNoticeInfo  
	 * @Description: 获取系统消息详情
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/getMsgNoticeInfo")
	@ResponseBody
	public ResultJSON getMsgNoticeInfo(@RequireValid MsgSysNoticeInfoVO  vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		try {
			
			UserMsgNotice  	noticeInfo=noticeService.getNoticeInfo(vo.getMsgCode());
			
			if(noticeInfo!=null){
				json.put("content",noticeInfo.getContent());
			}else{
				json.put("content","");
			}
			json.put("title", noticeInfo.getTitle());
			json.put("createTime", noticeInfo.getCreateTime());
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
			
			} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			log.error("获取系统通知详情-getMsgNoticeInfo:"+JSONObject.toJSONString(vo),e);
			throw new BusinessException().build("MSG_26001", "获取详情");
		}
		
		return resJson;
		
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: getMsgNoticeInfo  
	 * @Description: 获取系统消息详情
	 * @param vo
	 * @return
	 * @throws BusinessException    参数  
	 * @return: ResultJSON    返回类型
	 */
	@RequestMapping("/delMsgNotice")
	@ResponseBody
	public ResultJSON delMsgNotice(@RequireValid MsgSysNoticeInfoVO  vo) throws BusinessException{
		ResultJSON resJson=new ResultJSON();
		Message m = null;
		
		JSONObject json=new JSONObject();
		try {
			
			boolean state=noticeService.delNotice(vo.getMsgCode());
			
			if(state){
				json.put("result",0);
			}else{
				json.put("result","1");
			}
			
			m = PropertiesUtil.getMessage("COMMON_200");
			resJson.setReturnCode(m.getCode());
			resJson.setReturnTip(m.getTip());
			resJson.setBody(JSONObject.toJSON(json));
			
			} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException().build("MSG_26001", "删除");
		}
		
		return resJson;
		
	}
	
	
	
	
	

}