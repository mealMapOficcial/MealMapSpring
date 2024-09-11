package com.riwi.MealMap.controllers.impl;

import com.riwi.MealMap.controllers.interfaces.IIngredientController;
import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.impl.IngredientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController implements IIngredientController {

    @Autowired
    IngredientImpl ingredientService;

    @Override
    public ResponseEntity<Ingredient> create(IngredientsWithoutId entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Ingredient> readAll() {
        return List.of();
    }

    @Override
    @GetMapping("/readByName/{name}")
    public ResponseEntity<Ingredient> readByName(@PathVariable String name) {
        return ingredientService.readByName(name);
    }

    @Override
    public ResponseEntity<Ingredient> update(Integer integer, Ingredient ingredient) {
        return null;
    }
}
