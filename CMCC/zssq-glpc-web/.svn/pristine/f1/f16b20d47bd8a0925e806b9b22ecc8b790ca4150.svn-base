package com.zssq.auth.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
 * @author 赵翊
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
		
		// 获取访问链接
		String path = request.getServletPath();
		// 获取项目工程名字
		String projectName = request.getContextPath();
		// 组装成资源链接
		String powerUrl = projectName + path;
	
		if(!path.contains("login")){
			String token = request.getParameter("token");
			String userCode = request.getParameter("userCode");
			
			List<String> permissions = null;
			
			if(StringUtils.isBlank(token)){
		        request.setAttribute("authMsg", getResultJSON("COMMON_403", "未获取到有效的用户 token"));
			} 
			else if (StringUtils.isBlank(userCode)){	
		        request.setAttribute("authMsg", getResultJSON("COMMON_403", "未获取到有效的 userCode"));
			}
			else if (StringUtils.isNotBlank(userCode)) {
				if (StringUtils.contains(token, AppConstants.SAAS)) {
					// Saas管理员、超管登录时，校验传入的token是否与缓存中的相同，如果不同，则视为传入的token无效
					SysAdminInfo sysAdminInfo = (SysAdminInfo)saasTenantService.selectByRedisKey(userCode);
					if (sysAdminInfo != null) {
						// 可操作的接口映射列表
//						permissions = sysAdminInfo.getPermissions();
						String userToken = StringUtils.substringAfter(token, AppConstants.SAAS);
						String serverToken = StringUtils.substringAfter(sysAdminInfo.getToken(), AppConstants.SAAS);
						if (!StringUtils.equals(sysAdminInfo.getToken(), token) && Long.valueOf(userToken) < Long.valueOf(serverToken)) {
							request.setAttribute("authMsg", getResultJSON("AUTH_10008", "账号在其它地方登陆，当前用户 token 已过期，请重新登陆"));
						}
					} else {
						request.setAttribute("authMsg", getResultJSON("COMMON_403", "未获取到有效的 userCode"));
					}
				} else {
					// 普通用户登录时，校验传入的userCode是否有效
					SysUserInfo userInfo = sysUserService.selectByCode(userCode);
					if (userInfo == null) {
						request.setAttribute("authMsg", getResultJSON("COMMON_403", "未获取到有效的 userCode"));
					} else {
						// 可操作的接口映射列表
						permissions = userInfo.getPermissions();
					}
					
					PropertiesUtil propertiesUtil = new PropertiesUtil("security.properties");
					String excludePath = propertiesUtil.getProperty("excludePath");
					String[] excludeArry = StringUtils.split(excludePath, ",");
					List<String> excludeList = Arrays.asList(excludeArry);
					
					// 判断是否有调用接口的权限
					if(CollectionUtils.isEmpty(permissions)) {
						request.setAttribute("authMsg", getResultJSON("COMMON_403", "操作权限不足"));
					}
					else if (!permissions.contains(powerUrl) && !excludeList.contains(powerUrl)) {
						request.setAttribute("authMsg", getResultJSON("COMMON_403", "操作权限不足"));
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
	private ResultJSON getResultJSON(String returnCode, String msg) {
		
		Message message = PropertiesUtil.getMessage(returnCode);
		
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
