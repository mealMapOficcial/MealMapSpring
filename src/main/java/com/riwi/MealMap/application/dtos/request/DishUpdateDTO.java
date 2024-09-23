package com.riwi.MealMap.application.dtos.request;

import com.riwi.MealMap.domain.enums.TypeOfDishes;
import com.riwi.MealMap.domain.enums.TypeOfDrinks;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishUpdateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private float price;

    @NotNull
    private boolean promotion;

    @NotNull(message = "Type of Dishes is required")
    private TypeOfDishes typeOfDishes;

    @NotNull(message = "Ingredients are required")
    @Size(min = 1, message = "At least one ingredient is required")
    private List<IngredientUpdateDTO> ingredients;
}

