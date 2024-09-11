package com.riwi.MealMap.services.CRUD;

import org.springframework.http.ResponseEntity;

public interface Create<EntityRequest, Entity> {
    public ResponseEntity<Entity> create(EntityRequest entity);
}
