package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.request.Ingredient.DrinkWithoutId;
import com.riwi.MealMap.application.dtos.request.Ingredient.DrinkRequest;
import com.riwi.MealMap.application.services.generic.*;
import com.riwi.MealMap.domain.entities.Drink;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDrinkService extends
        CreateDTO<DrinkWithoutId, Drink>,
        ReadByName<Drink, String>,
        ReadAll<Drink>,
        ReadById<Drink, Integer>,
        Delete<Integer>,
        Update<Integer, Drink>,
        GetAvailableDrink<DrinkRequest> {
}
