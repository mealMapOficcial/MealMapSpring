package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.request.DrinkRequest;
import com.riwi.MealMap.application.dtos.request.DrinkWithoutId;
import com.riwi.MealMap.domain.entities.Drink;
import com.riwi.MealMap.domain.ports.service.IDrinkService;
import com.riwi.MealMap.infrastructure.config.annotations.FetchOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drink")
public class DrinkController implements IDrinkService {

    @Autowired
    IDrinkService drinkService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    @PostMapping("/create")
    public ResponseEntity<Drink> createDTO(@RequestBody DrinkWithoutId drink) {
        return drinkService.createDTO(drink);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        drinkService.delete(id);
    }

    @Override
    @GetMapping("/readAll")
    public List<Drink> readAll() {
        return drinkService.readAll();
    }

    @Override
    @GetMapping("/readById/{id}")
    public Optional<Drink> readById(@PathVariable Integer id) {
        String url = "http://localhost:3000/orders";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

        Optional<Drink> drink = drinkService.readById(id);

        return ResponseEntity.ok(drink).getBody();
    }

    @Override
    @GetMapping("/readByName/{name}")
    public ResponseEntity<Drink> readByName(@PathVariable String name) {
        return drinkService.readByName(name);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<Drink> update(@PathVariable Integer id,@RequestBody Drink drink) {
        return drinkService.update(id, drink);
    }

    @Override
    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<DrinkRequest> getAvailableDrink() {
        return this.drinkService.getAvailableDrink();

    }
}
