package com.riwi.MealMap.dtos.request.Ingredient;

import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.entities.TypeDish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private Integer typeDishId;

    @NotNull(message = "Ingrediens is required")
    private List<Integer> ingredientIds;

}
