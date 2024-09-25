package com.riwi.MealMap.application.dtos.exception;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericExceptions extends RuntimeException {

    public GenericExceptions(String message) {
        super(message);
    }

}
