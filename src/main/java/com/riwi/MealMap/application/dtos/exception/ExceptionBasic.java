package com.riwi.MealMap.application.dtos.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionBasic {
    private Integer code;
    private String status;
}
