package com.riwi.MealMap.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientsWithoutId {

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Price must be positive")
    private Float price;

    @NotBlank(message = "Measure is required")
    private String measure;
}
