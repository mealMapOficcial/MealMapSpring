package com.riwi.MealMap.services.interfaces;

import com.riwi.MealMap.dtos.request.Ingredient.TypeDishWithName;
import com.riwi.MealMap.entities.TypeDish;
import com.riwi.MealMap.services.CRUD.*;

public interface ITypeDishService extends
        CreateDTO<TypeDishWithName, TypeDish>,
        ReadByName<TypeDish, String>,
        ReadAll<TypeDish>,
        ReadById<TypeDish, Integer>,
        Delete<Integer>,
        Update<Integer, TypeDish> {
}
