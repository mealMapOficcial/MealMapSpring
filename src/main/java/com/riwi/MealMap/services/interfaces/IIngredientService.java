package com.riwi.MealMap.services.interfaces;

import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.CRUD.*;

public interface IIngredientService extends
        CreateDTO<IngredientsWithoutId, Ingredient>,
        ReadByName<Ingredient, String>,
        ReadAll<Ingredient>,
        ReadById<Ingredient, Integer>,
        Delete<Integer>,
        Update<Integer, Ingredient> {
}
