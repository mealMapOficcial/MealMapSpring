package com.riwi.MealMap.application.dtos.request.Ingredient;

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
public class IngredientsWithoutId {

    @NotNull
    private String name;

    private Float price;

    @NotNull
    private String measure;
}
