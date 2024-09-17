package com.riwi.MealMap.dtos.request.Ingredient;

import com.riwi.MealMap.entities.Dish;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDishWithName {
    @NotBlank(message = "Name is required")
    private String name;
}
