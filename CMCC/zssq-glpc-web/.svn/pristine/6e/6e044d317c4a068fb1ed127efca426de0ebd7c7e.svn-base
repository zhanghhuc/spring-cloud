package com.zssq.credit.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.CreditConstants;
import com.zssq.credit.vo.IntegralActionModifyVo;
import com.zssq.credit.vo.IntegralActionSearchVo;
import com.zssq.dao.pojo.IntegralAction;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IIntegralActionService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: IntegralActionController
 * @Description: 积分行为相关的服务
 * @author CaiZhaohui
 * @date 2017年4月11日
 *
 */
@Controller
@RequestMapping("/credit")
public class IntegralActionController {

	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(IntegralActionController.class);

	/** 积分行为服务 */
	@Autowired
	IIntegralActionService integralActionService;

	/**
	 * @Title: listIntegralAction @Description: 获取积分行为列表 @param @param
	 * param @param @return @param @throws BusinessException 参数 @return
	 * ResultJSON 返回类型 @throws
	 */
	@RequestMapping("/integralList")
	@ResponseBody
	public ResultJSON listIntegralAction(@RequireValid IntegralActionSearchVo param) throws BusinessException {
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			// 封装分页参数
			PageParam pageParam=new PageParam(Integer.parseInt(param.getPageNo()), Integer.parseInt(param.getPageSize()));
			// 封装积分行为
			IntegralAction record=new IntegralAction();
			// 设置积分行为类型
			record.setActionType(Byte.parseByte(param.getActionType()));
			
			// 分页查询积分行为列表
			PageBean pageBean = integralActionService.selectPage(pageParam, record);
			// 获取积分行为列表
			List<IntegralAction> integralActionList = pageBean.getRecordList();
			// 总记录数
			Integer totalCount = pageBean.getTotalCount();
			
			// 封装输出参数json数组
			JSONArray jsonArray=new JSONArray();
			
			if (CollectionUtils.isNotEmpty(integralActionList)) {
				for(IntegralAction integralAction:integralActionList){
					// 封装输出参数json对象
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("actionCode", StringTools.formatToString(integralAction.getActionCode()));
					jsonObject.put("actionRemark", StringTools.formatToString(integralAction.getActionRemark()));
					jsonObject.put("actionType", StringTools.formatToString(integralAction.getActionType()));
					jsonObject.put("actionCycle", StringTools.formatToString(integralAction.getActionCycle()));
					jsonObject.put("actionCycleCount", StringTools.formatToString(integralAction.getActionCycleCount()));
					jsonObject.put("expValue", StringTools.formatToString(integralAction.getExpValue()));
					jsonObject.put("integralValue", StringTools.formatToString(integralAction.getIntegralValue()));
					jsonObject.put("coinValue", StringTools.formatToString(integralAction.getCoinValue()));
					// json数组添加元素
					jsonArray.add(jsonObject);
				}
			}
			
			JSONObject body=new JSONObject();
			body.put("totalCount", totalCount);
			body.put("dataList", jsonArray);

			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_200);
			
			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "获取积分行为列表"));
			rj.setBody(body);
			return rj;
		} catch (Exception e) {
			logger.error("IntegralActionController.listIntegralAction 查询积分行为列表失败", e);
			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_17002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取积分行为列表"));
		}

	}

	/**
	 * @Title: modifyIntegralAction @Description: 修改积分行为 @param @param
	 * param @param @return @param @throws BusinessException 参数 @return
	 * ResultJSON 返回类型 @throws
	 */
	@RequestMapping("/integralModify")
	@ResponseBody
	public ResultJSON modifyIntegralAction(@RequireValid IntegralActionModifyVo param) throws BusinessException {
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			// 积分行为实体
			IntegralAction record = new IntegralAction();
			record.setActionCode(param.getActionCode());
			record.setExpValue(Byte.parseByte(param.getExpValue()));
			record.setCoinValue(Byte.parseByte(param.getCoinValue()));
			record.setIntegralValue(Byte.parseByte(param.getIntegralValue()));
			record.setModifyTime(System.currentTimeMillis());
			record.setActionCycleCount(Byte.parseByte(param.getActionCycleCount()));
			
			// 根据积分行为编号修改积分行为
			integralActionService.updateByActionCode(record);

			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_200);

			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "修改积分行为"));
			rj.setBody(new JSONObject());
			return rj;
		} catch (Exception e) {
			logger.error("IntegralActionController.modifyIntegralAction 更新积分行为失败", e);
			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_17002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "修改积分行为"));
		}
	}
	
}
