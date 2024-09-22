package com.riwi.MealMap.application.dtos.exception;


public class InsufficientIngredientsException extends RuntimeException {
    public InsufficientIngredientsException(String message) {
        super(message);
    }
}