package com.riwi.MealMap.services.interfaces;

import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.CRUD.*;

public interface IIngredientService extends
        Create<IngredientsWithoutId, Ingredient>,
        ReadByName<Ingredient, String>,
        ReadAll<Ingredient>,
        Delete<Integer>,
        Update<Integer, Ingredient> {
}
