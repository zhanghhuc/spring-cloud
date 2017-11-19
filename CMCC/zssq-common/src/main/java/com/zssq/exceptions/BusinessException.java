package com.zssq.exceptions;

import com.zssq.pojo.Message;
import com.zssq.utils.PropertiesUtil;

/**
 * 异常封装
 * @ClassName: BusinessException  
 * @Description: TODO  
 * @author lr  
 * @date 2017年1月4日  
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public String messageCode;
	/**
	 * 根据传入的messageCode构建异常信息
	 * @Function build
	 * @Description 
	 * @param messageCode
	 * @param args
	 * @return
	 */
	public static BusinessException build(String messageCode, Object... args) {
		String code = "";
		String tip = "";
		try {
			Message m = PropertiesUtil.getMessage(messageCode);
			code = m.getCode();
			tip = m.getTip();
		} catch (Exception e) {
			code = "000";
			tip = "未定义异常信息";
		}
		return new BusinessException(code, tip, args);
	}

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	public BusinessException(String messageCode, String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String messageCode, String message, Object... args) {
		super(String.format(message, args));
		this.messageCode = messageCode;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	
}
