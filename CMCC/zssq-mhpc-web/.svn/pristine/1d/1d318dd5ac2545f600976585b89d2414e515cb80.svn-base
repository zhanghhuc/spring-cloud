package com.zssq.auth.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.pojo.SysSkinInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysSkinService;
import com.zssq.utils.PropertiesUtil;
import com.zssq.utils.StringTools;

@Controller  
@RequestMapping("/skin")
public class SkinController {

	@Autowired
	private ISysSkinService sysSkinService;
	
	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@ResponseBody
	@RequestMapping("/selected")  
	public ResultJSON selected() throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		JSONObject body = new JSONObject();
		
		try {
			SysSkinInfo skin = sysSkinService.selectInUse();
			body.put("id", StringTools.formatToString(skin.getId()));
			body.put("name", StringTools.formatToString(skin.getName()));
			body.put("url", StringTools.formatToString(skin.getUrl()));
			body.put("module", StringTools.formatToString(skin.getModule()));
			body.put("imgUrl", StringTools.formatToString(skin.getImgUrl()));
			body.put("inuse", StringTools.formatToString(skin.getInuse()));
			body.put("style", StringTools.formatToString(skin.getStyle()));
			body.put("color", StringTools.formatToString(skin.getColor()));
			body.put("theme", StringTools.formatToString(skin.getTheme()));
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取选定皮肤"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("获取选定皮肤异常", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取选定皮肤"));
		}
	}
}
