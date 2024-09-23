package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.request.DishUpdateDTO;
import com.riwi.MealMap.application.dtos.request.DishWithoutId;
import com.riwi.MealMap.application.dtos.request.DrinkUpdateDTO;
import com.riwi.MealMap.application.services.generic.*;
import com.riwi.MealMap.application.dtos.request.DishWithoutIdAndWithDTO;
import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.domain.entities.Drink;

public interface IDishService extends
        CreateGeneric<DishWithoutId>,
        ReadByName<Dish, String>,
        ReadAll<Dish>,
        ReadById<Dish, Integer>,
        Delete<Integer>,
        UpdateDTO<Integer, DishUpdateDTO, Dish> {
}
