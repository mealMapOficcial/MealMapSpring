package com.riwi.MealMap.application.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.riwi.MealMap.application.dtos.exception.ExceptionBasic;
import com.riwi.MealMap.application.dtos.exception.ExceptionResponse;
import com.riwi.MealMap.application.dtos.exception.ExceptionsResponse;
import com.riwi.MealMap.application.dtos.exception.GenericNotFoundExceptions;
import com.riwi.MealMap.application.dtos.exception.IngredientNotFoundException;
import com.riwi.MealMap.application.dtos.exception.InsufficientIngredientsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  
  @ExceptionHandler(InsufficientIngredientsException.class)
  public ResponseEntity<String> handleInsufficientIngredientsException(InsufficientIngredientsException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

 
  @ExceptionHandler(IngredientNotFoundException.class)
  public ResponseEntity<String> handleIngredientNotFoundException(IngredientNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(GenericNotFoundExceptions.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ExceptionResponse handleGenericNotFoundException(GenericNotFoundExceptions exception) {
      return ExceptionResponse.builder()
              .code(HttpStatus.NOT_FOUND.value())
              .status(HttpStatus.NOT_FOUND.name())
              .message(exception.getMessage())
              .build();
  }

  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionBasic handleValidationExceptions(MethodArgumentNotValidException exception) {
      List<String> errors = new ArrayList<>();
      exception.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

      return ExceptionsResponse.builder()
              .code(HttpStatus.BAD_REQUEST.value())
              .status(HttpStatus.BAD_REQUEST.name())
              .errors(errors)
              .build();
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in the application format: " + ex.getMessage());
  }
}
