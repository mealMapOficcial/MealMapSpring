package com.riwi.MealMap.dtos.request.Ingredient;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientsWithoutId {

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private String weight;
}
