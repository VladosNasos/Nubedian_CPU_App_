package com.example.cpuapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse err = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }
        ErrorResponse err = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                LocalDateTime.now(),
                fieldErrors
        );
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAny(Exception ex) {
        ErrorResponse err = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    public static class ErrorResponse {
        public final int status;
        public final String message;
        public final LocalDateTime timestamp;
        public final Map<String, String> errors;

        public ErrorResponse(int status, String message, LocalDateTime timestamp, Map<String, String> errors) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
            this.errors = errors;
        }
    }
}
