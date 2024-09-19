package com.example.Currency_Gateway_API.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.warn("Validation failed: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return handleErrorResponse(request, errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        log.error("An unexpected error occurred: {}", ex.getMessage(), ex);

        String errorMessage = "An unexpected error occurred. Please try again later.";
        return handleErrorResponse(request, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handleErrorResponse(WebRequest request, Object body, HttpStatus status) {
        String acceptHeader = request.getHeader(HttpHeaders.CONTENT_TYPE);

        if (acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)) {
            return ResponseEntity.status(status)
                    .contentType(MediaType.APPLICATION_XML)
                    .body(convertToXmlError(body));
        } else {
            return ResponseEntity.status(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body);
        }
    }

    private String convertToXmlError(Object error) {
        StringBuilder xmlResponse = new StringBuilder();
        xmlResponse.append("<error>");

        if (error instanceof Map) {
            Map<String, String> errorMap = (Map<String, String>) error;
            errorMap.forEach((key, value) -> {
                xmlResponse.append("<attribute>")
                        .append("<name>").append(key).append("</name>")
                        .append("<message>").append(value).append("</message>")
                        .append("</attribute>");
            });
        } else {
            xmlResponse.append("<message>").append(error).append("</message>");
        }

        xmlResponse.append("</error>");
        return xmlResponse.toString();
    }
}