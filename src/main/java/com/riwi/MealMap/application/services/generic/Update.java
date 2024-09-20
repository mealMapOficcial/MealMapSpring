package com.riwi.MealMap.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface Update<ID, Entity> {
    public ResponseEntity<Entity> update(ID id, Entity entity);
}
