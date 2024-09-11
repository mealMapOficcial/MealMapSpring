package com.riwi.MealMap.controllers.interfaces;

import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.CRUD.*;

public interface IIngredientController extends
        Create<IngredientsWithoutId, Ingredient>,
        ReadByName<Ingredient, String>,
        ReadById<Ingredient, Integer>,
        ReadAll<Ingredient>,
        Delete<Integer>,
        Update<Integer, Ingredient> {
}
