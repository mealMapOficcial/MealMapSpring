package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.request.IngredientsWithoutId;
import com.riwi.MealMap.domain.entities.Ingredient;
import com.riwi.MealMap.application.services.impl.IngredientService;
import com.riwi.MealMap.domain.ports.service.IIngredientService;
import com.riwi.MealMap.infrastructure.config.annotations.FetchOrders;
import com.riwi.MealMap.infrastructure.persistence.StockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
@CrossOrigin("*")
@Transactional
public class IngredientController implements IIngredientService {

    @Autowired
    IngredientService ingredientService;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/create")
    @Override
    public ResponseEntity<Ingredient> createDTO(@Valid @RequestBody IngredientsWithoutId ingredient) {
        return ingredientService.createDTO(ingredient);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        stockRepository.deleteByIngredients_Id(id);

        ingredientService.delete(id);
    }

    @Override
    @GetMapping("/readAll")
    @FetchOrders
    public List<Ingredient> readAll() {
        return ingredientService.readAll();
    }

    @Override
    @GetMapping("/readById/{id}")
    @FetchOrders
    public Optional<Ingredient> readById(@PathVariable Integer id) {

        Optional<Ingredient> ingredient = ingredientService.readById(id);

        return ResponseEntity.ok(ingredient).getBody();
    }

    @Override
    @GetMapping("/readByName/{name}")
    @FetchOrders
    public ResponseEntity<Ingredient> readByName(@PathVariable String name) {
        return ingredientService.readByName(name);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable Integer id,@RequestBody Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }

}
