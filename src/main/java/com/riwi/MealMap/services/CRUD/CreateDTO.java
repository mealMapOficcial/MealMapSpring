package com.riwi.MealMap.services.CRUD;

import org.springframework.http.ResponseEntity;

public interface CreateDTO<EntityRequest, Entity> {
    public ResponseEntity<Entity> createDTO(EntityRequest entity);
}
