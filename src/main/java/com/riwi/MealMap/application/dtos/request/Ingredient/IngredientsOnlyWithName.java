package com.riwi.MealMap.application.dtos.request.Ingredient;

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
    @NotNull
    private String name;
}
