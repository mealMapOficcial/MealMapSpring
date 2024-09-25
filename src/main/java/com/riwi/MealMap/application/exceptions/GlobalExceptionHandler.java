package com.riwi.MealMap.application.exceptions;

import com.riwi.MealMap.application.dtos.exception.GenericExceptions;
import com.riwi.MealMap.application.dtos.exception.IngredientNotFoundException;
import com.riwi.MealMap.application.dtos.exception.InsufficientIngredientsException;
import com.riwi.MealMap.application.dtos.exception.StockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientIngredientsException.class)
    public ResponseEntity<String> handleInsufficientIngredientsException(InsufficientIngredientsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<String> handleIngredientNotFoundException(IngredientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<String> handleStockNotFoundException(StockNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(GenericExceptions.class)
    public ResponseEntity<String> handleGenericNotFoundException(GenericExceptions ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

}
