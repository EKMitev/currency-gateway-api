package com.example.Currency_Gateway_API.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequestIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRequestId {

    String message() default "Duplicate request Id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
