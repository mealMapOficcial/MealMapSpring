package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.request.Ingredient.DishWithoutId;
import com.riwi.MealMap.application.dtos.request.Ingredient.DishWithoutIdAndWithDTO;
import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.application.services.impl.DishService;
import com.riwi.MealMap.domain.ports.service.IDishService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import com.riwi.MealMap.application.dtos.exception.ExceptionResponse;
import com.riwi.MealMap.application.dtos.exception.ExceptionsResponse;

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
    public List<Dish> readAll() {
        return dishService.readAll();
    }


    @GetMapping("/readById/{id}")
    public Optional<Dish> readById(@PathVariable Integer id) {
        String url = "http://localhost:3000/orders";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

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
    public List<DishWithoutIdAndWithDTO> getAvailableDish() {
        try{
            return this.dishService.getAvailableDish();
        } catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR FOUND DISHES");
        }

    }
}
