package com.riwi.MealMap.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientsOnlyWithName {
    @NotBlank(message="not null")
    private String name;

    @NotNull(message="not null")
    private double  quantity;
}
