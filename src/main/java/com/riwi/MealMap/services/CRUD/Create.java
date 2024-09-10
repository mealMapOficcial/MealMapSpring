package com.riwi.MealMap.services.CRUD;

import org.springframework.http.ResponseEntity;

public interface Create<Entity> {
    public ResponseEntity<Entity> create(Entity entity);
}
