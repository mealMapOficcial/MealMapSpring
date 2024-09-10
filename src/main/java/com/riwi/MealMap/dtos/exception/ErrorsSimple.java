package com.riwi.MealMap.dtos.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ErrorsSimple {
    private Integer code;
    private String status;
}
