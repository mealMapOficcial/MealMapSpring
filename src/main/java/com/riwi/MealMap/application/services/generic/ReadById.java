package com.riwi.MealMap.application.services.generic;

import java.util.Optional;

public interface ReadById<Entity, ID>{
    public Optional<Entity> readById(ID id);
}
