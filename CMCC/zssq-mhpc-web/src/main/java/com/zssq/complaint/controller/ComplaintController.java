package com.zssq.complaint.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.complaint.vo.AddComplaintVo;
import com.zssq.constants.ComplaintConstants;
import com.zssq.dao.pojo.ComplaintInfo;
import com.zssq.dao.pojo.RelaPersonComplaint;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ComplaintService;
import com.zssq.utils.StringTools;
import com.zssq.vote.controller.BaseController;

/**
 * 举报的controller
 * @author POWER
 *
 */
@RequestMapping("/complaint")
@Controller
public class ComplaintController extends BaseController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ComplaintService complaintService;
	
	/**
	 * 添加举报信息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value="/jubao",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addComplaint(@RequireValid AddComplaintVo vo) throws BusinessException{
		
		try {
			SysUserInfo userInfo = getUserInfo(vo.getUserCode());
			if(userInfo == null){
				//用户信息不存在
				throw BusinessException.build("COMPLAINT_20002","用户信息");
			}
			String tenantCode = userInfo.getTenantCode();
			String orgCode = userInfo.getManageOrgInfo().getSysOrgCode();
			
			//校验infoUrl
			JSONObject jsonObject = JSONObject.parseObject(vo.getInfoUrl());
			if (!jsonObject.containsKey("appCode") || !jsonObject.containsKey("commentCode")
					|| !jsonObject.containsKey("replyCode") || !jsonObject.containsKey("dynamicCode")) {
				throw BusinessException.build("COMMON_402","infoUrl");
			}
			//校验微博和博客应用本身被举报是必须有dynamicCode
			String appCode = (String) jsonObject.get("appCode");
			String commentCode = (String) jsonObject.get("commentCode");
			String replyCode = (String) jsonObject.get("replyCode");
			String dynamicCode = (String) jsonObject.get("dynamicCode");
			if(ComplaintConstants.MBOLG.equals(Byte.parseByte(vo.getApplication())) 
					|| ComplaintConstants.BOLG.equals(Byte.parseByte(vo.getApplication()))){
				if(StringTools.isEmpty(dynamicCode) && StringTools.isNotEmpty(appCode) &&
						StringTools.isEmpty(commentCode) && StringTools.isEmpty(replyCode)){
					throw BusinessException.build("COMMON_402","dynamicCode");
				}
			}
			
			/** 查询该内容的举报记录*/
			ComplaintInfo complaintInfo = complaintService.getComplaintInfo(vo.getInfoCode(),tenantCode);
			
			if( complaintInfo != null){
				/** 该内容已经被举报*/
				//查询该内容的所有举报人
				List<RelaPersonComplaint> list = complaintService.selectComplaintPerson(complaintInfo.getComplaintInfoCode(),tenantCode);
				
				/** 同一个人是否举报了同一个内容   */
				boolean flag = false;
				for (RelaPersonComplaint p : list) {
					if(p.getComplainantCode().equals(vo.getUserCode())){
						flag = true;
						break;
					}
				}
				if(flag){
					//该用户已经举报了该内容
					throw BusinessException.build("COMPLAINT_20003");
				}else{
					//该用户没有举报过该内容
					//举报次数加一
					//添加举报人
					ComplaintInfo info = new ComplaintInfo();
					info.setTenantCode(tenantCode);
					info.setComplaintInfoCode(complaintInfo.getComplaintInfoCode());
					info.setComplaintCount(complaintInfo.getComplaintCount()+1);
					
					RelaPersonComplaint person = new RelaPersonComplaint();
					person.setComplainantCode(vo.getUserCode());
					person.setReason(Byte.parseByte(vo.getReason()));
					person.setComplaintCode(complaintInfo.getComplaintInfoCode());
					person.setOrgCode(orgCode);
					person.setTenantCode(tenantCode);
					
					complaintService.updateComplaintInfoAndInsertPerson(info,person);
				}
			}else{
				/** 该内容没有被举报*/
				//添加举报信息
				ComplaintInfo info = new ComplaintInfo();
				info.setTenantCode(tenantCode);
				info.setOrgCode(orgCode);
				info.setInfoCode(vo.getInfoCode());
				info.setInfoUrl(vo.getInfoUrl());
				info.setApplication(Byte.parseByte(vo.getApplication()));
				info.setPublisherCode(vo.getPublisherCode());
				info.setPublishTime(Long.parseLong(vo.getPublishTime()));
				SysUserInfo publisherInfo = getUserInfo(vo.getPublisherCode());
				if(publisherInfo != null){
					info.setPublisherName(publisherInfo.getUserName());
					info.setPublisherOrg(publisherInfo.getManageOrgInfo().getSysOrgName());
					info.setPublisherOrgCode(publisherInfo.getManageOrgInfo().getSysOrgCode());
				}
				
				//添加举报人信息
				RelaPersonComplaint person = new RelaPersonComplaint();
				person.setOrgCode(orgCode);
				person.setTenantCode(tenantCode);
				person.setComplainantCode(vo.getUserCode());
				person.setReason(Byte.parseByte(vo.getReason()));
				
				complaintService.addComplaintAndPerson(info,person);
			}
			
			ResultJSON result = new ResultJSON("COMMON_200");
			result.setBody(new JSONObject());
			return result;
		}  catch (BusinessException e) {
			throw e;
		}  catch (Exception e) {
			log.error("ComplaintController.addComplaint", e);
			throw BusinessException.build("COMMON_400");
		}
	}
	
}
