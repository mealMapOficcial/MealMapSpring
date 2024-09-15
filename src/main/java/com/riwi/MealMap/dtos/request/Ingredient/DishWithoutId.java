package com.riwi.MealMap.dtos.request.Ingredient;

import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.entities.TypeDishes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishWithoutId {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Float price;

    @NotNull(message = "TypeDishes is required")
    private TypeDishes typeDishes;

    @NotNull(message = "Ingrediens is required")
    private Set<Ingredient> ingredient;
}
