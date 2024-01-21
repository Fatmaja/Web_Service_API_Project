package org.example.projectwebservice1.controller.controllerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        for (ObjectError globalError : ex.getBindingResult().getGlobalErrors()) {
            errors.add(new ValidationError(globalError.getObjectName(), globalError.getDefaultMessage()));
        }

        ValidationErrorResponse errorResponse = new ValidationErrorResponse("Validation error", errors);

        return ResponseEntity.badRequest().body(errorResponse);
    }

    // Inner classes for better organization
    record ValidationError(String field, String message) {
    }

    record ValidationErrorResponse(String error, List<ValidationError> errors) {
    }
}
