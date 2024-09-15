package com.riwi.MealMap.services.interfaces;

import com.riwi.MealMap.dtos.request.Ingredient.DishWithoutId;
import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.CRUD.*;
import org.springframework.http.ResponseEntity;

public interface IDishService extends
        CreateDTO<DishWithoutId, Dish>,
        ReadByName<Dish, String>,
        ReadAll<Dish>,
        ReadById<Dish, Integer>,
        Delete<Integer>,
        Update<Integer, Dish> {
}
