package com.riwi.MealMap.dtos.request.Ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsWithoutId {

    @NotNull
    private String name;

    private Float price;

    @NotNull
    private String weight;
}
