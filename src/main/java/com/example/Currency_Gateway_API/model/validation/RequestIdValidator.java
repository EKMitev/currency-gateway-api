package com.example.Currency_Gateway_API.model.validation;

import com.example.Currency_Gateway_API.service.RequestLogService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestIdValidator implements ConstraintValidator<UniqueRequestId, String> {

    @Autowired
    private RequestLogService requestLogService;

    @Override
    public void initialize(UniqueRequestId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // If it's null or empty, @NotBlank handle this
        }

        return !requestLogService.isRequestDuplicated(value);
    }
}
