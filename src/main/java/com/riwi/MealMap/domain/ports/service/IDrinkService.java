package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.request.DrinkWithoutId;
import com.riwi.MealMap.application.dtos.request.DrinkRequest;
import com.riwi.MealMap.application.services.generic.*;
import com.riwi.MealMap.domain.entities.Drink;

public interface IDrinkService extends
        CreateDTO<DrinkWithoutId, Drink>,
        ReadByName<Drink, String>,
        ReadAll<Drink>,
        ReadById<Drink, Integer>,
        Delete<Integer>,
        Update<Integer, Drink>,
        GetAvailableDrink<DrinkRequest> {
}
