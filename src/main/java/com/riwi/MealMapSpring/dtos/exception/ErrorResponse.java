package com.riwi.MealMapSpring.dtos.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {


    private String message;
    private String details;
}
