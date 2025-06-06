package com.fcm_ms.token_api.exception;

import java.util.Map;
import java.util.HashMap;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fcm_ms.token_api.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<ErrorResponse> handlePathValidationException(
    HandlerMethodValidationException ex,
    HttpServletRequest request) {

    Map<String, String> error = new HashMap<>();
    error.put("path_parameter", "Invalid path parameter format: only numbers allowed");

    ErrorResponse errorResponse = ErrorResponse.of(
      HttpStatus.BAD_REQUEST.value(),
      "Invalid path parameter",
      error,
      request.getRequestURI()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleBodyValidationExceptions(
      MethodArgumentNotValidException ex,
      HttpServletRequest request) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    ErrorResponse errorResponse = ErrorResponse.of(
      HttpStatus.BAD_REQUEST.value(),
      "Invalid fields",
      errors,
      request.getRequestURI()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleEmptyRequestBody(
      HttpMessageNotReadableException ex,
      HttpServletRequest request) {

    Map<String, String> errors = new HashMap<>();
    errors.put("requestBody", "Request body is missing or malformed");

    ErrorResponse errorResponse = ErrorResponse.of(
      HttpStatus.BAD_REQUEST.value(),
      "Empty or invalid request body",
      errors,
      request.getRequestURI()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
