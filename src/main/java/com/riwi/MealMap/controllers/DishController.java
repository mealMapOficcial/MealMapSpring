package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.request.DishWithoutId;
import com.riwi.MealMap.application.dtos.request.DishWithoutIdAndWithDTO;
import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.application.services.impl.DishService;

import com.riwi.MealMap.infrastructure.config.annotations.FetchOrders;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import com.riwi.MealMap.application.dtos.exception.ExceptionsResponse;
import com.riwi.MealMap.application.dtos.exception.GenericNotFoundExceptions;

@RestController
@RequestMapping("/dish")
public class DishController  {

    @Autowired
    DishService dishService;

    @Autowired
    RestTemplate restTemplate;


    @PostMapping("/create")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Dish created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ExceptionsResponse.class)))
    })
    public ResponseEntity<?> create(@RequestBody DishWithoutId dish) {
        
            DishWithoutId dishEntity = this.dishService.createGeneric(dish);
            return ResponseEntity.status(HttpStatus.CREATED).body(dishEntity);

    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        dishService.delete(id);
    }

    @GetMapping("/readAll")
    @FetchOrders
    public List<Dish> readAll() {
        return dishService.readAll();
    }

    @GetMapping("/readById/{id}")
    @FetchOrders
    public Optional<Dish> readById(@PathVariable Integer id) {

        Optional<Dish> dish = dishService.readById(id);

        return ResponseEntity.ok(dish).getBody();
    }

    @GetMapping("/readByName/{name}")
    @FetchOrders
    public ResponseEntity<Dish> readByName(@PathVariable String name) {
        return dishService.readByName(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Dish> update(@PathVariable Integer id,@RequestBody Dish dish) {
        return dishService.update(id, dish);
    }
}
