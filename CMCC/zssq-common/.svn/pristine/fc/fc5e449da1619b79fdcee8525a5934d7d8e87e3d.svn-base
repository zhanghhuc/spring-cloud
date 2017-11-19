package com.zssq.annotation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.zssq.annotation.validation.impl.MoneyImpl;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = MoneyImpl.class)
public @interface Money {

	boolean required() default true;
	
    String message() default "{money.invalid.message}";
  
    Class<?>[] groups() default {};
  
    Class<? extends Payload>[] payload() default {};
}
