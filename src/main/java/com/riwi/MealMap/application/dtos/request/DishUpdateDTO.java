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

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishUpdateDTO {

    private String name;
    private Float price;
    private Boolean promotion;
    private String imageUrl;
    private TypeOfDishes typeOfDishes;
    private List<IngredientUpdateDTO> ingredients = new ArrayList<>();
}

