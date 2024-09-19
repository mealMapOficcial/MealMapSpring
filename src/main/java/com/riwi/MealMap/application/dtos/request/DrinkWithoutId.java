package com.riwi.MealMap.application.dtos.request;

import com.riwi.MealMap.domain.enums.TypeOfDrinks;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkWithoutId {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Float price;

    @NotNull
    private boolean promotion;

    @NotNull(message = "TypeDishes is required")
    @Enumerated(EnumType.STRING)
    private TypeOfDrinks typeOfDrinks;

    @NotNull(message = "Ingrediens is required")
    private ArrayList<IngredientsOnlyWithName> ingredients;
}
