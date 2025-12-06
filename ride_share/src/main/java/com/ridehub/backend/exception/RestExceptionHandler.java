package com.ridehub.backend.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex
    ) {
        return buildErrorResponse("RESOURCE_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidRequest(
            InvalidRequestException ex
    ) {
        return buildErrorResponse("INVALID_REQUEST", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        String errorMessage = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return buildErrorResponse("VALIDATION_FAILURE", errorMessage, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            String errorType,
            String message,
            HttpStatus status
    ) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error_type", errorType);
        responseBody.put("error_message", message);
        responseBody.put("timestamp", Instant.now());
        return new ResponseEntity<>(responseBody, status);
    }
}
