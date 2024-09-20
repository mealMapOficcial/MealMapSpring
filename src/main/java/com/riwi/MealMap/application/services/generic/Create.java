package com.riwi.MealMap.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface Create<EntityRequest, Entity> {
    public ResponseEntity<Entity> create(EntityRequest entity);
}
