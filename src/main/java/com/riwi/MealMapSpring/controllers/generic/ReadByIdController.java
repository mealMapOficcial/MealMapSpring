package com.riwi.MealMapSpring.controllers.generic;

import org.springframework.http.ResponseEntity;

public interface ReadByIdController<Entity, ID> {
    public ResponseEntity<Entity> readById(ID id);
}
