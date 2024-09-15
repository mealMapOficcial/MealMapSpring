package com.riwi.MealMap.services.CRUD;
import com.riwi.MealMap.entities.Dish;

import java.util.Optional;

public interface ReadById<Entity, ID>{
    public Optional<Entity> readById(ID id);
}
