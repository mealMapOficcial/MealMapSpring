package com.riwi.MealMapSpring.controllers.generic;

import org.springframework.http.ResponseEntity;

public interface CreateController<EntityRequest, Entity> {
    public ResponseEntity<Entity> create(EntityRequest entity);
}
