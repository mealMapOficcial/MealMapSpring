package com.riwi.MealMap.dtos.request.Ingredient;

import com.riwi.MealMap.entities.Dish;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDishWithoutId {
    @NotBlank(message = "Name is required")
    private String name;

    @OneToMany(mappedBy = "typeDishes")
    private List<Dish> dish;
}
