package com.riwi.MealMap.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface ReadByName<Entity, NAME>{
    public ResponseEntity<Entity> readByName(NAME name);
}
