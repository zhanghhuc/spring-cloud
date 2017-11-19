package com.zssq.annotation.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zssq.annotation.validation.LongType;


public class LongTypeImpl implements ConstraintValidator<LongType, String> {

	private boolean required;

	private String expression;

	@Override
	public void initialize(LongType constraintAnnotation) {
		this.required = constraintAnnotation.required();
		this.expression = constraintAnnotation.expression();
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
		try {
			long val = Long.parseLong(value);
			if (">0".equals(expression)) {
				if (val > 0) {
					return true;
				} else {
					return false;
				}
			} else if ("<0".equals(expression)) {
				if (val < 0) {
					return true;
				} else {
					return false;
				}
			} else if (">=0".equals(expression)) {
				if (val >= 0) {
					return true;
				} else {
					return false;
				}
			} else if ("<=0".equals(expression)) {
				if (val <= 0) {
					return true;
				} else {
					return false;
				}
			} else if ("<>0".equals(expression)) {
				if (val != 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
