package com.riwi.MealMap.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface CreateDTO<EntityRequest, Entity> {
    public ResponseEntity<Entity> createDTO(EntityRequest entity);
}
