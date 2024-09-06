package com.riwi.MealMapSpring.controllers.generic;

import org.springframework.http.ResponseEntity;

public interface Create<EntityRequest, Entity> {
    public ResponseEntity<Entity> create(EntityRequest entity);
}
