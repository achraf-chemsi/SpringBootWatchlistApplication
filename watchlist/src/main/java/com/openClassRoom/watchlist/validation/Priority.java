package com.openClassRoom.watchlist.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriorityValidator.class)
public @interface Priority {
	
	String message() default "please enter L, M or H for priority";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
