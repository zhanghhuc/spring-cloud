package com.zssq.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zssq.constants.AppConstants;
import com.zssq.dao.pojo.SysAdminInfo;
import com.zssq.dao.pojo.SysUserInfo;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.service.ISaasTenantService;
import com.zssq.service.ISysUserService;
import com.zssq.utils.PropertiesUtil;

/**
 * 权限验证拦截器，用于验证接口操作权限，即是否可以调用指定接口；<br>
 * 该拦截器始终返回true，即不中断请求过程；<br>
 * 当权限不足时，在Request请求对象中设置标记，最终使用SpringAspect切面将提示信息返回给调用者
 * 
 * @since  JDK 1.7
 */
public class AuthInterceptor implements HandlerInterceptor {

	/** Saas 业务处理组件 */
	@Autowired
	private ISaasTenantService saasTenantService;
	
	/** 用户信息 业务处理组件 */
	@Autowired
	private ISysUserService sysUserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		// 获取访问链接
		String path = request.getServletPath();
	
		if(!path.contains("login")){
			String token = request.getParameter("token");
			String userCode = request.getParameter("userCode");
			
			if(StringUtils.isBlank(token)){
		        request.setAttribute("authMsg", getResultJSON("未获取到有效的用户 token"));
			} 
			else if (StringUtils.isBlank(userCode)){	
		        request.setAttribute("authMsg", getResultJSON("未获取到有效的 userCode"));
			}
			else if (StringUtils.isNotBlank(userCode)) {
				if (StringUtils.contains(token, AppConstants.SAAS)) {
					// Saas管理员、超管登录时，校验传入的token是否与缓存中的相同，如果不同，则视为传入的token无效
					SysAdminInfo sysAdminInfo = (SysAdminInfo)saasTenantService.selectByRedisKey(userCode);
					if (sysAdminInfo != null) {
						if (!StringUtils.equals(sysAdminInfo.getToken(), token)) {
							request.setAttribute("authMsg", getResultJSON("未获取到有效的 token"));
						}
					} else {
						request.setAttribute("authMsg", getResultJSON("未获取到有效的 token"));
					}
				} else {
					// 普通用户登录时，校验传入的userCode是否有效
					SysUserInfo userInfo = sysUserService.selectByCode(userCode);
					if (userInfo == null) {
						request.setAttribute("authMsg", getResultJSON("未获取到有效的 userCode"));
					}
				}				
			}	
		}
		return true;
	}
	
	/**
	 * 获取权限验证返回结果
	 * 
	 * @param msg
	 * 			返回结果描述信息
	 * @return 统一返回结果对象 ResultJSON
	 */
	private ResultJSON getResultJSON(String msg) {
		
		Message message = PropertiesUtil.getMessage("COMMON_403");
		
		ResultJSON rj = new ResultJSON();
		rj.setReturnCode(message.getCode());
        rj.setReturnTip(message.getTip());
        rj.setBody(msg);
        
        return rj;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}
}
