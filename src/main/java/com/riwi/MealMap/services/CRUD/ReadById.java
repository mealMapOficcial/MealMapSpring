package com.riwi.MealMap.services.CRUD;
import com.riwi.MealMap.entities.Ingredient;

import java.util.Optional;

public interface ReadById<Entity, ID>{
    public Optional<Ingredient> readById(ID id);
}
