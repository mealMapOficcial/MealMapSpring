package com.riwi.MealMapSpring.services.CRUD;

public interface ReadById<Entity, ID> {
    public Entity readById(ID id);
}
