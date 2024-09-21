package com.riwi.MealMap.application.dtos.request;

import java.util.List;

import com.riwi.MealMap.domain.enums.TypeOfDishes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

public class DishwhitIngredientsName {

     @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Float price;

    @NotNull(message = "required")
    private boolean promotion;

    @NotNull(message = "TypeDishes is required")
    @Enumerated(EnumType.STRING)
    private TypeOfDishes typeOfDishes;

    @NotNull(message = "Ingrediens is required")
    private List<IngredientsWithName> ingredients;
    

}
