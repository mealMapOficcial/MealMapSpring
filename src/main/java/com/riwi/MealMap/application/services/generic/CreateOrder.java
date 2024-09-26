package com.riwi.MealMap.application.services.generic;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CreateOrder<Response, Entity> {
    ResponseEntity<Response> createOrder(Entity entity);
}
