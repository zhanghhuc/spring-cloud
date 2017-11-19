package com.zssq.annotation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.zssq.annotation.validation.impl.DateImpl;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = DateImpl.class)
public @interface Date {

	boolean required() default true;
	
	String pattern() default "yyyy-MM-dd HH:mm:ss";
	
    String message() default "{date.invalid.message}";  
  
    Class<?>[] groups() default {};  
  
    Class<? extends Payload>[] payload() default {};
}
