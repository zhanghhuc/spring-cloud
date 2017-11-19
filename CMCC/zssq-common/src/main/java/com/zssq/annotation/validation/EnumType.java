package com.zssq.annotation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.zssq.annotation.validation.impl.EnumTypeImpl;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = EnumTypeImpl.class)
public @interface EnumType {

	boolean required() default true;
	
	String[] allow();
	
    String message() default "{format.message}";  
  
    Class<?>[] groups() default {};  
  
    Class<? extends Payload>[] payload() default {};
}
