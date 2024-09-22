package com.riwi.MealMap.application.services.generic;

import org.springframework.http.ResponseEntity;

public interface UpdateDTO<ID, DTO, Entity> {
    ResponseEntity<Entity> updateDTO(ID id, DTO dto);
}
