package com.riwi.MealMapSpring.dtos.Response;

import com.riwi.MealMapSpring.Entities.Ingredients;
import com.riwi.MealMapSpring.Enum.TypeOfDishes;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishResponse {
    private String name;
    private double price;
    private TypeOfDishes typeOfDishes;
    private boolean promotion;
    private List<IngredientsResponse> ingredients;


}
