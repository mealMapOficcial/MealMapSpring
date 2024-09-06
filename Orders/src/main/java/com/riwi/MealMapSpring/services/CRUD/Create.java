package com.riwi.MealMapSpring.services.CRUD;

public interface Create<EntityRequest, Entity> {
    public Entity create(EntityRequest entity);
}
