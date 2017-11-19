package com.zssq.annotation.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zssq.annotation.validation.Mobile;


public class MobileImpl implements ConstraintValidator<Mobile, String> {
	
	private boolean required;

	@Override
	public void initialize(Mobile constraintAnnotation) {
		this.required = constraintAnnotation.required();
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
		Pattern p = Pattern.compile("^[1][3,4,5,6,7,8][0-9]{9}$"); // 验证手机号
		Matcher m = p.matcher(value);
		if (m.matches()) {
			return true;
		}
		return false;
	}

}
