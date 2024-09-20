package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.services.generic.*;
import com.riwi.MealMap.application.dtos.request.IngredientsWithoutId;
import com.riwi.MealMap.domain.entities.Ingredient;

public interface IIngredientService extends
        CreateDTO<IngredientsWithoutId, Ingredient>,
        ReadByName<Ingredient, String>,
        ReadAll<Ingredient>,
        ReadById<Ingredient, Integer>,
        Delete<Integer>,
        Update<Integer, Ingredient> {
}
