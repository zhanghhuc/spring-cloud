package com.zssq.annotation.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zssq.annotation.validation.Money;


public class MoneyImpl implements ConstraintValidator<Money, String> {
	
	private boolean required;

	@Override
	public void initialize(Money constraintAnnotation) {
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
	
	private boolean validation(String value){
		Pattern p = Pattern.compile("^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$"); // 验证金额
		Matcher m = p.matcher(value);
		if (m.matches()) {
			return true;
		}
		return false;
	}

}
