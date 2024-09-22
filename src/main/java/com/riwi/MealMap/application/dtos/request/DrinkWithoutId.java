package com.riwi.MealMap.application.dtos.request;

import com.riwi.MealMap.domain.enums.TypeOfDrinks;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class DrinkWithoutId {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Float price;

    @NotNull(message = "Promotion is required")
    private boolean promotion;

    @NotNull(message = "Type of Drinks is required")
    @Enumerated(EnumType.STRING)
    private TypeOfDrinks typeOfDrinks;

    @NotNull(message = "Ingredients are required")
    @Size(min = 1, message = "At least one ingredient is required")
    private List<IngredientsOnlyWithName> ingredients;
}
