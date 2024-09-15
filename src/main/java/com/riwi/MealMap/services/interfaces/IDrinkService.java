package com.riwi.MealMap.services.interfaces;

import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Drink;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.CRUD.*;

public interface IDrinkService extends
        Create<Drink>,
        ReadByName<Drink, String>,
        ReadAll<Drink>,
        ReadById<Drink, Integer>,
        Delete<Integer>,
        Update<Integer, Drink> {
}
