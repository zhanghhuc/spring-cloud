package com.zssq.annotation.validation.impl;

import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zssq.annotation.validation.Date;


public class DateImpl implements ConstraintValidator<Date, String> {
	
	private boolean required;
	
	private String pattern;

	@Override
	public void initialize(Date constraintAnnotation) {
		this.required = constraintAnnotation.required();
		this.pattern = constraintAnnotation.pattern();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// 检查是否可以为空
		if (required) {
			if (value == null || "".equals(value)) {
				return false;
			}
			return validation(value);
		} else {
			if (value == null || "".equals(value)) {
				return true;
			} else {
				return validation(value);
			}
		}
	}
	
	private boolean validation(String value) {
		if (value.length() != pattern.length()) {
			return false;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		// 设置lenient为false.
		// 否则SimpleDateFormat会比较宽松地验证日期，比如2004/02/29会被接受，并转换成2004/03/01
		format.setLenient(false);
		try {
			format.parse(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
