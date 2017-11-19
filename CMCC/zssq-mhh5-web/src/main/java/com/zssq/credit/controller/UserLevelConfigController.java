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
import com.zssq.credit.vo.UserLevelConfigSerachVo;
import com.zssq.dao.pojo.UserLevelConfig;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.IUserLevelConfigService;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

/**
 * 
 * @ClassName: UserLevelConfigController  
 * @Description: 经验值等级配置相关的服务   
 * @author power  
 * @date 2017年5月5日  
 *
 */
@Controller
@RequestMapping("/credit")
public class UserLevelConfigController {

	/** 打印日志 */
	private static Logger logger = LoggerFactory.getLogger(UserLevelConfigController.class);

	/** 经验值等级配置服务 */
	@Autowired
	IUserLevelConfigService userLevelConfigService;

	/**
	 * 
	 * @Title: getExpAndLevel  
	 * @Description: 获取经验和等级列表
	 * @param @param param
	 * @param @return
	 * @param @throws BusinessException    参数  
	 * @return ResultJSON    返回类型  
	 * @throws
	 */
	@RequestMapping("/config")
	@ResponseBody
	public ResultJSON getExpAndLevel(@RequireValid UserLevelConfigSerachVo param) throws BusinessException {
		ResultJSON rj = new ResultJSON();
		Message message = null;
		try {
			// 查询经验值等级配置总列表
			List<UserLevelConfig> configList = userLevelConfigService.selectAll();
			
			// 封装输出参数json数组
			JSONArray jsonArray = new JSONArray();
			
			if (CollectionUtils.isNotEmpty(configList)) {
				for(UserLevelConfig config:configList){
					// 封装输出参数json对象
					JSONObject jsonObject = new JSONObject();
					if (config.getExpValue() == null) {
						jsonObject.put("expValue", StringTools.formatToString(config.getExpValueStart()) + "+");
					}else{
						jsonObject.put("expValue", StringTools.formatToString(config.getExpValueStart()) + "~"
								+ StringTools.formatToString(config.getExpValue()));
					}
					jsonObject.put("levelNo", StringTools.formatToString(config.getLevelNo()));
					jsonObject.put("levelTitle", StringTools.formatToString(config.getLevelTitle()));
					jsonArray.add(jsonObject);
				}
			}

			JSONObject body = new JSONObject();
			body.put("dataList", jsonArray);

			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_200);

			rj.setReturnCode(message.getCode());
			rj.setReturnTip(String.format(message.getTip(), "获取经验和等级列表"));
			rj.setBody(body);
			return rj;
		} catch (Exception e) {
			logger.error("UserLevelConfigController.getExpAndLevel 查询经验和等级列表失败", e);
			message = PropertiesUtil.getMessage(CreditConstants.RETURNCODE_CREDIT_17002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取经验和等级列表"));
		}
	}

}
