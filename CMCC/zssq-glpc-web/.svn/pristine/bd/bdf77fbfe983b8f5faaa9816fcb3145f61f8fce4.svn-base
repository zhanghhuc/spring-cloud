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
import com.zssq.credit.vo.IntegralAccountDetailSearchVo;
import com.zssq.dao.pojo.IntegralAccountDetail;
import com.zssq.dao.pojo.IntegralAction;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IIntegralAccountDetailService;
import com.zssq.service.IIntegralActionService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: IntegralAccountDetailController
 * @Description: 积分明细相关的服务
 * @author power
 * @date 2017年4月12日
 *
 */
@Controller
@RequestMapping("/credit")
public class IntegralAccountDetailController {

	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(IntegralActionController.class);

	/** 积分明细服务 */
	@Autowired
	IIntegralAccountDetailService integralAccountDetailService;

	/** 积分行为服务 */
	@Autowired
	IIntegralActionService integralActionService;

	/**
	 * 
	 * @Title: listIntegralAccountDetail @Description: 积分明细查询 @param @param
	 * param @param @return @param @throws BusinessException 参数 @return
	 * ResultJSON 返回类型 @throws
	 */
	@RequestMapping("/detailList")
	@ResponseBody
	public ResultJSON listIntegralAccountDetail(@RequireValid IntegralAccountDetailSearchVo param)
			throws BusinessException {
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			// 封装分页参数
			PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),
					Integer.parseInt(param.getPageSize()));
			// 封装积分明细
			IntegralAccountDetail record = new IntegralAccountDetail();
			// 设置账户编号
			record.setAccountCode(param.getAccountCode());

			// 分页查询积分明细
			PageBean pageBean = integralAccountDetailService.selectPage(pageParam, record);

			// 积分明细列表
			List<IntegralAccountDetail> recordList = pageBean.getRecordList();
			// 总记录数
			Integer totalCount = pageBean.getTotalCount();

			// 封装输出参数json数组
			JSONArray jsonArray = new JSONArray();
			
			if (CollectionUtils.isNotEmpty(recordList)) {
				for (IntegralAccountDetail recordTemp : recordList) {
					// 封装输出参数json对象
					JSONObject jsonObject = new JSONObject();
					if (recordTemp != null) {
						String actionRemark = ""; // 积分行为描述
						// 根据积分行为编号获取积分行为信息
						IntegralAction integralAction = integralActionService
								.selectByActionCode(recordTemp.getActionCode());
						if (integralAction != null) {
							actionRemark = integralAction.getActionRemark();
						}
						jsonObject.put("actionRemark", StringTools.formatToString(actionRemark));
						// 积分账户明细分类：1-增加 ，2-减少
						Byte accountDetailTypeTemp = recordTemp.getAccountDetailType();
						if (accountDetailTypeTemp == 1) {
							// 积分值为正
							jsonObject.put("currentValue", StringTools.formatToString(recordTemp.getCurrentValue()));  // 当前增减值
						} else {
							// 积分值为负或零
							jsonObject.put("currentValue", StringTools.formatToString(-recordTemp.getCurrentValue())); // 当前增减值
						}
						jsonObject.put("currentBalance", StringTools.formatToString(recordTemp.getCurrentBalance()));  // 当前增减后余额
						jsonObject.put("createTime", StringTools.formatToString(recordTemp.getCreateTime()));          // 创建时间
						// json数组添加元素
						jsonArray.add(jsonObject);
					}
				}
			}

			JSONObject body = new JSONObject();
			body.put("totalCount", totalCount);
			body.put("dataList", jsonArray);

			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_200);

			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "获取积分明细列表"));
			rj.setBody(body);
			return rj;
		} catch (Exception e) {
			logger.error("IntegralAccountDetailController.listIntegralAccountDetail 查询积分明细列表失败", e);
			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_17002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取积分明细列表"));
		}
	}

}
