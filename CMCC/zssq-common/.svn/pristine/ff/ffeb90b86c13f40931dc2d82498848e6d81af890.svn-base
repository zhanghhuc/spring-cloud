package com.zssq.annotation.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zssq.annotation.validation.EnumType;


public class EnumTypeImpl implements ConstraintValidator<EnumType, String> {
	
	private boolean required;
	
	private String[] allow;

	@Override
	public void initialize(EnumType constraintAnnotation) {
		this.required = constraintAnnotation.required();
		this.allow = constraintAnnotation.allow();
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
		for (String s : allow) {
			if (s.equals(value)) {
				return true;
			}
		}
		return false;
	}

}
