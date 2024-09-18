package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.services.generic.*;
import com.riwi.MealMap.domain.entities.Drink;

public interface IDrinkService extends
        Create<Drink>,
        ReadByName<Drink, String>,
        ReadAll<Drink>,
        ReadById<Drink, Integer>,
        Delete<Integer>,
        Update<Integer, Drink> {
}
