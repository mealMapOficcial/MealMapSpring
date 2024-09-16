package com.riwi.MealMapSpring.dtos.exception;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    public ErrorResponse(String message) {}


    private String message;
    private String details;
}
