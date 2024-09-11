package com.riwi.MealMap.services.CRUD;

import org.springframework.http.ResponseEntity;

public interface Update<ID, Entity> {
    public ResponseEntity<Entity> update(ID id, Entity entity);
}
