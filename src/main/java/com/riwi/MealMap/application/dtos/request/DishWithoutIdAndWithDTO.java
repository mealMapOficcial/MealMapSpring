package com.riwi.MealMap.application.dtos.request;

import com.riwi.MealMap.domain.enums.TypeOfDishes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.riwi.MealMap.Valirdation.InvalidateEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishWithoutIdAndWithDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Float price;

    @NotNull
    private boolean promotion;
    @InvalidateEnum
    @NotNull(message = "TypeDishes is required")
    @Enumerated(EnumType.STRING)
    private TypeOfDishes typeOfDishes;

    @NotNull(message = "Ingrediens is required")
    private List<IngredientsOnlyWithName> ingredients;

}
