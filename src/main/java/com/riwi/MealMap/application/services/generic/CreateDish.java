package com.riwi.MealMap.application.services.generic;

public interface CreateDish <DishWithIngredients, DishIngredientsOnlyName> {
    public DishIngredientsOnlyName createDish(DishWithIngredients dishWithIngredients);

}
