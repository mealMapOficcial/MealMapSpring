package com.riwi.MealMap.application.exceptions;

import com.riwi.MealMap.application.dtos.exception.ExceptionBasic;
import com.riwi.MealMap.application.dtos.exception.ExceptionResponse;
import com.riwi.MealMap.application.dtos.exception.ExceptionsResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import com.riwi.MealMap.application.dtos.exception.GenericExceptions;

@RestControllerAdvice

public class BadRequest {

    @ExceptionHandler(GenericExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleIngredientNotFoundException(GenericExceptions exception) {
        return ExceptionResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ExceptionBasic badRequest(Exception exception){

        List<String> errors = new ArrayList<>();

        if (exception instanceof MethodArgumentNotValidException e){
            e.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        } else {
            errors.add(exception.getMessage());
        }

        return ExceptionsResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
    }


 

   
}
