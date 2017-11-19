package com.zssq.auth.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.auth.vo.SkinChooseVo;
import com.zssq.auth.vo.SkinPageVo;
import com.zssq.constants.AuthConstants;
import com.zssq.dao.pojo.SysSkinInfo;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISysSkinService;
import com.zssq.utils.PageBean;
import com.zssq.utils.PageParam;
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
	@RequestMapping("/selectPage")  
	public ResultJSON selectPage(@RequireValid SkinPageVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		JSONObject body = new JSONObject();
		
		try {
        	PageParam pageParam = new PageParam(Integer.parseInt(param.getPageNo()),Integer.parseInt(param.getPageSize()));
			SysSkinInfo record = new SysSkinInfo();
			if(StringTools.isNotEmpty(param.getColor())){
				record.setColor(param.getColor());
			}
			if(StringTools.isNotEmpty(param.getStyle())){
				record.setStyle(param.getStyle());
			}
			if(StringTools.isNotEmpty(param.getTheme())){
				record.setTheme(param.getTheme());
			}
			PageBean pageBean = sysSkinService.selectPage(record, pageParam);
			List<SysSkinInfo> dataList = pageBean.getRecordList();
			JSONArray jsonArray= new JSONArray();
			for (int i = 0; i < dataList.size(); i++) {
				JSONObject info = new JSONObject();
				info.put("id", StringTools.formatToString(dataList.get(i).getId()));
				info.put("name", StringTools.formatToString(dataList.get(i).getName()));
				info.put("url", StringTools.formatToString(dataList.get(i).getUrl()));
				info.put("module", StringTools.formatToString(dataList.get(i).getModule()));
				info.put("imgUrl", StringTools.formatToString(dataList.get(i).getImgUrl()));
				info.put("inuse", StringTools.formatToString(dataList.get(i).getInuse()));
				info.put("style", StringTools.formatToString(dataList.get(i).getStyle()));
				info.put("color", StringTools.formatToString(dataList.get(i).getColor()));
				info.put("theme", StringTools.formatToString(dataList.get(i).getTheme()));
				jsonArray.add(info);
			}
			body.put("dataList", jsonArray);
			body.put("totalCount", pageBean.getTotalCount());
			
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "获取皮肤"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("获取皮肤异常", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "获取皮肤"));
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/choose")  
	public ResultJSON choose(@RequireValid SkinChooseVo param) throws BusinessException {
		
		Message message = null;
		ResultJSON rj = new ResultJSON();
		JSONObject body = new JSONObject();
		
		try {
			sysSkinService.setInUse(Long.parseLong(param.getId()));
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_200);
			rj.setReturnCode(message.getCode());
	        rj.setReturnTip(String.format(message.getTip(), "选择皮肤"));
	        rj.setBody(body);
			
			return rj;
		} catch (Exception e) {
			log.error("选择皮肤异常", e);
			message = PropertiesUtil.getMessage(AuthConstants.RETURNCODE_AUTH_10002);
			throw new BusinessException(message.getCode(), String.format(message.getTip(), "选择皮肤"));
		}
	}
	
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
