package com.riwi.MealMap.services.CRUD;

import org.springframework.http.ResponseEntity;

public interface ReadByName<Entity, NAME>{
    public ResponseEntity<Entity> readByName(NAME name);
}
