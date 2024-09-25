package com.riwi.MealMap.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface Create<Entity> {
    public ResponseEntity<Entity> create(Entity entity);
}
