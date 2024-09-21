package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.exception.ExceptionsResponse;
import com.riwi.MealMap.application.dtos.exception.GenericNotFoundExceptions;
import com.riwi.MealMap.application.dtos.request.DrinkWithoutId;
import com.riwi.MealMap.application.dtos.request.DrinkWithoutIdAndWithDTO;
import com.riwi.MealMap.application.services.impl.DrinkService;
import com.riwi.MealMap.domain.entities.Drink;
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

@RestController
@RequestMapping("/drink")
public class DrinkController {

    @Autowired
    DrinkService drinkService;

    @Autowired
    RestTemplate restTemplate;


    @PostMapping("/create")
    @FetchOrders
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Drink created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ExceptionsResponse.class)))
    })
    public ResponseEntity<?> create(@RequestBody DrinkWithoutId dish) {

        DrinkWithoutId dishEntity = this.drinkService.createGeneric(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(dishEntity);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        drinkService.delete(id);
    }


    @GetMapping("/readAll")
    @FetchOrders
    public List<Drink> readAll() {
        return drinkService.readAll();
    }


    @GetMapping("/readById/{id}")
    @FetchOrders
    public Optional<Drink> readById(@PathVariable Integer id) {

        Optional<Drink> drink = drinkService.readById(id);

        return ResponseEntity.ok(drink).getBody();
    }


    @GetMapping("/readByName/{name}")
    @FetchOrders
    public ResponseEntity<Drink> readByName(@PathVariable String name) {
        return drinkService.readByName(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Drink> update(@PathVariable Integer id,@RequestBody Drink drink) {
        return drinkService.update(id, drink);
    }


    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<DrinkWithoutIdAndWithDTO> getAvailableDish() {
        try{
            return this.drinkService.getAvailableDrink();
        } catch (Exception e){

            throw new GenericNotFoundExceptions( "ERROR FOUND DISHES");
        }

    }
}
