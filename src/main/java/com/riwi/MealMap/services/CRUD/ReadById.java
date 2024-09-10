package com.riwi.MealMap.services.CRUD;

import org.springframework.http.ResponseEntity;

public interface ReadById <Entity, ID>{
    public ResponseEntity<Entity> readById(ID id);
}
