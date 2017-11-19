package com.zssq.filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.zssq.annotation.RequireValid;
import com.zssq.constants.AuthConstants;
import com.zssq.exceptions.BusinessException;
import com.zssq.pojo.Message;
import com.zssq.pojo.ResultJSON;
import com.zssq.utils.PropertiesUtil;

/**
 * 公用切面，适用于所有请求
 * 
 * @ClassName: SpringAspect
 * @Description:
 * @author lr
 * @date 2017年1月4日
 */
@Aspect
@Order(1)
public class SpringAspect {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(this.getClass());

	private Validator validator;
	
	/** 请求处理开始时间 */
	private long startTimeMillis = 0; 
	
	/** 请求处理结束时间 */
	private long endTimeMillis = 0; 

	/**
	 * 定义 Controller 层切入点
	 */
	@Pointcut("execution(* com.zssq.*.controller.*.*(..))")
	public void controllerAspect() {
		
	}

	/**
	 * 环绕通知类型，在切入点逻辑执行前后做增强处理
	 * 
	 * @param joinPoint
	 * @return
	 */
	@Around("controllerAspect()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) {
		
		startTimeMillis = System.currentTimeMillis();
		Object result = null;

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();

		String className = joinPoint.getTarget().getClass().getName();
		String methodName = method.getName();
		Annotation[][] argAnnotations = method.getParameterAnnotations();
		Object[] args = joinPoint.getArgs();
		
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			ServletRequestAttributes sra = (ServletRequestAttributes) ra;
			HttpServletRequest request = sra.getRequest();
			Object requestParams = JSONObject.toJSON(request.getParameterMap());

			// 登录验证属性，该属性不为空时，表示验证失败，中断请求
			Object ticketMsgAuth = request.getAttribute("ticketMsgAuth");
			if (ticketMsgAuth != null) { 
				ResultJSON resultJSON = JSONObject.parseObject(String.valueOf(ticketMsgAuth), ResultJSON.class);
				return resultJSON;
			}

			// 接口访问权限验证，该属性不为空时，表示验证失败，中断请求
			ResultJSON authMsg = (ResultJSON) request.getAttribute("authMsg");
			if (authMsg != null && (StringUtils.equals(AuthConstants.RETURNCODE_COMMON_403, authMsg.getReturnCode())
					|| StringUtils.equals("10008", authMsg.getReturnCode()))) {
				return authMsg;
			}

			// 前置通知
			logger.info("The method " + methodName + " start. param<" + requestParams + ">");

			// 校验请求参数
			for (int i = 0; i < args.length; i++) {
				if (hasValidAnnotations(argAnnotations[i])) {
					validateArg(args[i]);
				}
			}

			// 执行目标方法
			result = joinPoint.proceed();
			
			// 返回通知
			logger.info("The method " + methodName + " end. result<" + JSONObject.toJSON(result) + ">");
			
			endTimeMillis = System.currentTimeMillis();
			
			// 格式化开始时间
			String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
			// 格式化结束时间
			String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis);

			logger.info("操作方法名:" + methodName + " 操作开始时间:" + startTime + " 操作结束时间:" + endTime);
		} catch (BusinessException be) {
			ResultJSON rj = new ResultJSON();
			rj.setReturnCode(be.getMessageCode());
			rj.setReturnTip(be.getMessage());
			rj.setBody(new JSONObject());

			result = rj;
			logger.error(className + "." + methodName + ":" + JSONObject.toJSON(result), be);
		} catch (Throwable e) {
			// 异常通知
			Message m = PropertiesUtil.getMessage("COMMON_999");

			ResultJSON rj = new ResultJSON();
			rj.setReturnCode(m.getCode());
			rj.setReturnTip(m.getTip());
			rj.setBody(new JSONObject());

			result = rj;
			logger.error(className + "." + methodName + ":", e);
		}
		return result;
	}

	/**
	 * 判断参数是否包含 RequireValid 注解
	 * 
	 * @param annotations
	 * @return
	 */
	private boolean hasValidAnnotations(Annotation[] annotations) {
		
		for (Annotation annotation : annotations) {
			if (RequireValid.class.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 校验请求参数的合法性
	 * 
	 * @param arg
	 * @throws BusinessException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private void validateArg(Object arg) throws BusinessException, IllegalArgumentException, IllegalAccessException {

		Field[] fields = arg.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String className = field.getType().getSimpleName();
			
			if ("String".equalsIgnoreCase(className)) {
				Set<ConstraintViolation<Object>> violations = validator.validateProperty(arg, field.getName());
				if (violations.size() > 0) {
					for (ConstraintViolation<Object> violation : violations) {
						Message m = PropertiesUtil.getMessage("COMMON_402");
						throw new BusinessException(m.getCode(), String.format(m.getTip(), field.getName()));
					}
				}
			}
			
			if ("List".equalsIgnoreCase(className)) {
				// 如果是List类型的属性，继续校验List中每个对象中属性
				Object property = field.get(arg);
				if (property != null) {
					List ls = (List) property;
					for (Object object : ls) {
						validateArg(object);
					}
				}
			}
		}
	}

	@Required
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}