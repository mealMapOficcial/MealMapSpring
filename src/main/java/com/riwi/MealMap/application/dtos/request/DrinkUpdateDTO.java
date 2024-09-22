package com.riwi.MealMap.application.dtos.request;

import com.riwi.MealMap.domain.enums.TypeOfDrinks;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkUpdateDTO {
    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    private float price;

    @NotNull
    private boolean promotion;

    @NotNull
    private TypeOfDrinks typeOfDrinks;

    @NotNull(message= "Ingredients is required")
    private List<IngredientUpdateDTO> ingredients;
}
