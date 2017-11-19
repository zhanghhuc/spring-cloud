package com.zssq.credit.controller;

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
import com.zssq.constants.CreditConstants;
import com.zssq.credit.vo.IntegralActionSearchVo;
import com.zssq.dao.pojo.IntegralAction;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IIntegralActionService;
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
	 * 
	 * @Title: listIntegralAction  
	 * @Description: 获取积分行为列表
	 * @param @param param
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/integralList")
	@ResponseBody
	public ResultJSON listIntegralAction(@RequireValid IntegralActionSearchVo param) throws BusinessException {
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			// 分数变化类型：0-所有，1-加分，2-减分
			String valueType = param.getValueType();
			// 积分行为类型：1-个人，2-班组，3-公司
			Byte actionType = Byte.parseByte(param.getActionType());

			// 封装积分行为列表
			List<IntegralAction> list = null;
			
			if ("0".equals(valueType)) {
				// 根据积分行为类型查询积分行为列表
				list = integralActionService.selectAll(actionType);
			} else if ("1".equals(valueType)) {
				// 根据积分行为类型，查询积分值为正值的积分行为列表
				list = integralActionService.selectByIntegralPlus(actionType);
			} else if ("2".equals(valueType)) {
				// 根据积分行为类型，查询积分值为负值和零的积分行为列表
				list = integralActionService.selectByIntegralMinus(actionType);
			}

			// 封装输出参数json数组
			JSONArray jsonArray = new JSONArray();

			if (list != null && !list.isEmpty()) {
				for (IntegralAction integralAction : list) {
					// 封装输出参数json对象
					JSONObject jsonObject = new JSONObject();
					
					jsonObject.put("actionCode", StringTools.formatToString(integralAction.getActionCode())); // 积分行为编号
					jsonObject.put("actionRemark", StringTools.formatToString(integralAction.getActionRemark())); // 积分行为描述
					jsonObject.put("actionType", StringTools.formatToString(integralAction.getActionType())); // 积分行为编号
					jsonObject.put("actionCycle", StringTools.formatToString(integralAction.getActionCycle())); // 周期范围
					jsonObject.put("actionCycleCount", StringTools.formatToString(integralAction.getActionCycleCount())); // 周期内奖励次数
					jsonObject.put("expValue", StringTools.formatToString(integralAction.getExpValue())); // 经验值
					jsonObject.put("integralValue", StringTools.formatToString(integralAction.getIntegralValue())); // 积分
					jsonObject.put("coinValue", StringTools.formatToString(integralAction.getCoinValue())); // 金币
					jsonArray.add(jsonObject);
				}
			}

			JSONObject body = new JSONObject();
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

}
