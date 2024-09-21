package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.request.DishWithoutId;
import com.riwi.MealMap.application.dtos.request.DishWithoutIdAndWithDTO;
import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.application.services.impl.DishService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import com.riwi.MealMap.application.dtos.exception.ExceptionsResponse;
import com.riwi.MealMap.application.dtos.exception.GenericNotFoundExceptions;
import com.riwi.MealMap.application.dtos.request.DishwhitIngredientsName;

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
    public ResponseEntity<?> create( @Valid @RequestBody DishWithoutId dish) {
        
        DishwhitIngredientsName dishEntity = this.dishService.createDish(dish);
            return ResponseEntity.status(HttpStatus.CREATED).body(dishEntity);
           

        }
        
    


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        dishService.delete(id);
    }


    @GetMapping("/readAll")
    public List<Dish> readAll() {
        return dishService.readAll();
    }


    @GetMapping("/readById/{id}")
    public Optional<Dish> readById(@PathVariable Integer id) {

        Optional<Dish> dish = dishService.readById(id);

        return ResponseEntity.ok(dish).getBody();
    }


    @GetMapping("/readByName/{name}")
    public ResponseEntity<Dish> readByName(@PathVariable String name) {
        return dishService.readByName(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Dish> update(@PathVariable Integer id,@RequestBody Dish dish) {
        return dishService.update(id, dish);
    }


    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<DishwhitIngredientsName> getAvailableDish() {
        try{
            return this.dishService.getAvailableDish();
        } catch (Exception e){

            throw new GenericNotFoundExceptions( "ERROR FOUND DISHES");
        }

    }
}
