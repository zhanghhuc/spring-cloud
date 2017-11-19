package com.zssq.sso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.dao.pojo.SSO;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Login;
import com.zssq.pojo.LoginInfo;
import com.zssq.pojo.Logout;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISSOService;
import com.zssq.utils.DESUtil;
import com.zssq.utils.PropertiesUtil;

/**
 * 登录认证控制器
 * 
 * @since  JDK 1.7
 * @author 赵翊
 */
@Controller  
@RequestMapping("/sso")
public class SSOController {
	
	/** 日志记录器 */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** sso 服务组件 */
	@Autowired
	private ISSOService ssoService;  
	
	/**
	 * 登录认证，生成票据
	 * 
	 * @param param
	 * 			封装操作所需参数
	 * @return 通用返回对象
	 * @throws BusinessException
	 */
	@RequestMapping("/certification")  
	@ResponseBody
    public ResultJSON certification(@RequireValid Login param) throws BusinessException{  
		
		Message m = null;
		ResultJSON rj = new ResultJSON();	
		try {
			// 解密传入的登录认证参数
			String uidPassword = null;			
			if(StringUtils.isNotBlank(param.getUidPassword())){
				uidPassword = DESUtil.decrypt(param.getUidPassword());
			}
			String uid = DESUtil.decrypt(param.getUserCode());
			String app = DESUtil.decrypt(param.getApp());
			
			// 调用 sso 服务，生成票据
			String ticket = ssoService.certification(uid, uidPassword, app);			
			if(StringUtils.isBlank(ticket)){				
				m = PropertiesUtil.getMessage("SSO_0004");		// 认证失败
			}else{
		    	m = PropertiesUtil.getMessage("COMMON_200");	// 认证成功
			}
			
			LoginInfo login = new LoginInfo();
			login.setUserCode(uid);
			login.setApp(app);
			login.setToken(ticket);			
        	rj.setBody(login);
            
        	rj.setReturnCode(m.getCode());
            rj.setReturnTip(m.getTip());
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessException.build("SSO_0001", "登录认证，请求新票据出错");
		}
        return rj;
	}
	
//	@RequestMapping("/logout")  
//	@ResponseBody
//    public ResultJSON logout(HttpServletRequest request,HttpServletResponse response,@RequireValid Logout param) throws BusinessException{
//		Message m = null;
//		ResultJSON rj = new ResultJSON();
//		try {
//			String uid = DESUtil.decrypt(param.getUserCode());
//			String app = DESUtil.decrypt(param.getApp());
//			String ticket = DESUtil.decrypt(param.getToken());
//			
//			ssoService.logout(uid,app,ticket);
//			
//	    	m = PropertiesUtil.getMessage("COMMON_200");
//	    	
//	    	rj.setReturnCode(m.getCode());
//            rj.setReturnTip(m.getTip());
//            rj.setBody(new JSONObject());
//            
//            log.info("SSOController.logout:注销成功，" + uid + "-" + app);
//			
//		} catch (Exception e) {
//			throw BusinessException.build("SSO_0001", "注销");
//		}
//		
//		return rj;
//	}
	
//	@RequestMapping("/getLoginInfo")  
//	@ResponseBody
//    public ResultJSON getLoginInfo(HttpServletRequest request,HttpServletResponse response) throws BusinessException{
//		Message m = null;
//		ResultJSON rj = new ResultJSON();
//		try {
//			List<SSO> ssos = ssoService.updateInfo();
//			
//	    	m = PropertiesUtil.getMessage("COMMON_200");
//	    	
//	    	JSONObject jo = new JSONObject();
//        	jo.put("ssos", ssos);
//	    	
//	    	rj.setReturnCode(m.getCode());
//            rj.setReturnTip(m.getTip());
//            rj.setBody(jo);
//            
//            log.info("SSOController.getLoginInfo:获取用户登录信息成功");
//			
//		} catch (Exception e) {
//			throw BusinessException.build("SSO_0001", "获取用户登录信息");
//		}
//		
//		return rj;
//	}
	
}
