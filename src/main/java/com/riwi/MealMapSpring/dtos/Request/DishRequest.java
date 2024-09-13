package com.riwi.MealMapSpring.dtos.Request;

import com.riwi.MealMapSpring.Enum.TypeOfDishes;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DishRequest {


    private  String name;
    private double price;
    private double quatity;
    private TypeOfDishes typeOfDishes;
    private List<RequestIngredients> ingredients;
}
