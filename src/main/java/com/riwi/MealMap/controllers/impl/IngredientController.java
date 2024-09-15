package com.riwi.MealMap.controllers.impl;

import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.impl.IngredientService;
import com.riwi.MealMap.services.interfaces.IIngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientController implements IIngredientService {

    @Autowired
    IngredientService ingredientService;

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
        ingredientService.delete(id);
    }

    @Override
    @GetMapping
    public List<Ingredient> readAll() {
        return ingredientService.readAll();
    }

    @Override
    @GetMapping("/readById/{id}")
    public Optional<Ingredient> readById(@PathVariable Integer id) {
        String url = "http://localhost:3000/orders";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

        Optional<Ingredient> ingredient = ingredientService.readById(id);

        return ResponseEntity.ok(ingredient).getBody();
    }

    @Override
    @GetMapping("/readByName/{name}")
    public ResponseEntity<Ingredient> readByName(@PathVariable String name) {
        return ingredientService.readByName(name);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable Integer id,@RequestBody Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }

}
